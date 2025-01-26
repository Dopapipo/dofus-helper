package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.camel.client.ResourceStockClient;
import fr.pantheonsorbonne.camel.producers.PlantTransportProducer;
import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.exception.ResourceRequestDeniedException;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
// Plein de logique pour savoir quand envoyer une plante morte ou une plante mature à un magasin
@ApplicationScoped
public class PlantService {
    @Inject
    PlantRepository plantRepository;

    @Inject
    LogService logService;

    @Inject
    ResourceStockClient resourceStockClient;

    @Inject
    PlantTransportProducer plantTransportProducer;

    private PlantEntity feedPlant(PlantEntity plant, int quantity, PlantStat stat) throws ResourceRequestDeniedException {
        resourceStockClient.requestResource(stat.getType(), quantity);
        plant.feed(stat.getType(), quantity);
        return plant;
    }


    public void processHourlyPlantLifecycle() {
        List<PlantEntity> plants = plantRepository.findAll();
        this.triggerPlantGrowth(plants);
        this.triggerPlantNourishment(plants);
    }

    public void processDailyCycle() {
        Iterable<PlantEntity> plants = plantRepository.findAll();
        this.sendPlants(plants);


    }

    private void sendPlants(Iterable<PlantEntity> plants) {
        try {
            for (PlantEntity plant : plants) {
                try {
                    PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);

                    if (plant.isDead() && !plant.getComposted()) {

                        plantTransportProducer.sendDeadPlant(plantDTO);

                        plant.setComposted(true);
                        plantRepository.save(plant);

                        logService.sendLogPlantDiedOrSold(PlantMapper.toPlantDiedLog(plant));
                    }
                    else if (!plant.isDead() && plant.isMature() && !plant.isSold()) {
                        plantTransportProducer.sendPlantToStore(plantDTO);

                        plant.setSold(true);
                        plantRepository.save(plant);

                        logService.sendLogPlantDiedOrSold(PlantMapper.toPlantSoldLog(plant));
                    }
                } catch (Exception e) {
                    System.err.println("Erreur lors du traitement de la plante avec ID " + plant.getId() + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi des plantes : " + e.getMessage());
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
                        System.out.println("Failed to nourish plant " + plant.getId() + " for stat " + stat.getType() + ": " + e.getMessage());
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
        return (!plant.isDead() && plant.getRemainingTicksOfHealthyFor(plantStat) < 10);
    }

}
