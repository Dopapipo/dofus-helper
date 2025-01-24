package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.camel.client.ResourceStockClient;
import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.exception.ResourceRequestDeniedException;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

import java.util.List;

@ApplicationScoped
public class PlantService {
    @Inject
    PlantRepository plantRepository;
    @Inject
    ProducerTemplate producerTemplate;
    @Inject
    LogService logService;

    @Inject
    ResourceStockClient resourceStockClient;

    private PlantEntity feedPlant(PlantEntity plant, int quantity, PlantStat stat) throws ResourceRequestDeniedException {
        resourceStockClient.requestResource(stat.getType(), quantity);
        plant.feed(stat.getType(), quantity);
        return plant;
    }

    public void processHouryPlantLifecycle() {
        List<PlantEntity> plants = plantRepository.findAll();
        this.triggerPlantGrowth(plants);
        this.triggerPlantNourishment(plants);
    }

    public void processDailyCycle() {
        Iterable<PlantEntity> plants = plantRepository.findAll();
        this.sendPlants(plants);


    }


    private void sendPlants(Iterable<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            if (plant.isDead() && !plant.getComposted()) {
                try {
                    producerTemplate.sendBodyAndHeader("direct:plantQueue", PlantMapper.toPlantDTO(plant), "dead", true);
                    logService.sendLogPlantDiedOrSold(PlantMapper.toPlantDiedLog(plant));
                } catch (Exception e) {
                    System.out.println("Failed to send dead plant to transport: " + e.getMessage());
                }
            } else if (!plant.isDead() && plant.isMature() && !plant.isSold()) {
                producerTemplate.sendBodyAndHeader("direct:plantQueue", PlantMapper.toPlantDTO(plant), "sold", false);
                logService.sendLogPlantDiedOrSold(PlantMapper.toPlantSoldLog(plant));
            }
        }
    }


    private void triggerPlantNourishment(Iterable<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            for (PlantStat stat : plant.getStats()) {
                if (plantNeedsNourishmentForStat(plant, stat)) {
                    int requiredResourceQuantity = stat.getOptimalRessourceQuantityToFeed();
                    try {
                        PlantEntity updatedPlant = this.feedPlant(plant, requiredResourceQuantity, stat);
                        plantRepository.save(updatedPlant);
                    } catch (ResourceRequestDeniedException e) {
                        System.out.println("Resource request denied for " + stat.getType() + ": " + e.getMessage());
                    }
                }
            }
        }
    }

    private void triggerPlantGrowth(Iterable<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            if (!plant.isDead()) {
                plant.grow();
                PlantEntity updatedPlant = plantRepository.save(plant);
                logService.sendLogPlantCreatedOrUpdated(PlantMapper.toPlantUpdatedLog(updatedPlant));
            }
        }
    }

    private boolean plantNeedsNourishmentForStat(PlantEntity plant, PlantStat plantStat) {
        return (!plant.isDead() && plant.getRemainingTicksOfHealthyFor(plantStat) < 3);
    }

}
