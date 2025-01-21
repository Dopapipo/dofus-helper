package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.LogMessage;
import fr.pantheonsorbonne.dto.OperationTag;
import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.dto.ResourceRequest;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.entity.plant.stat.StatType;
import fr.pantheonsorbonne.exception.ResourceRequestDeniedException;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PlantService {
    @Inject
    PlantRepository plantRepository;

    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    @RestClient
    StockService stockService;

    public void processHourlyLifecycle() {
        List<PlantEntity> plants = plantRepository.findAll();
        this.tryToFeedThemPlants(plants);
        this.takeCareOfPlants(plants);
    }

    private void tryToFeedThemPlants(List<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            for (PlantStat stat : plant.getStats()) {
                if (this.plantNeedsNourishmentForStat(plant, stat)) {
                    PlantEntity updatedPlant = this.feedPlant(plant, stat);
                    plantRepository.save(updatedPlant);
                    this.sendLog(PlantMapper.toPlantUpdatedLog(updatedPlant));
                }
            }
        }

    }

    private PlantEntity feedPlant(PlantEntity plant, PlantStat stat) {
        try {
            int requiredResourceQuantity = stat.getOptimalRessourceQuantityToFeed();
            this.requestResource(stat.getType(), requiredResourceQuantity);
            plant.feed(stat.getType(), requiredResourceQuantity);
        } catch (ResourceRequestDeniedException e) {

        }
        return plant;
    }


    public void processDailyCycle() {
        Iterable<PlantEntity> plants = plantRepository.findAll();
        for (PlantEntity plant : plants) {
            if (plant.isDead() && !plant.getComposted()) {
                try {
                    this.sendLog(PlantMapper.toPlantDiedLog(plant));
                    plant.setComposted(true);
                    plantRepository.save(plant);
                } catch (Exception e) {
                }
            }
            this.sendPlantsToSellDaily(plant);
        }
        ;

    }


    private void takeCareOfPlants(Iterable<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            if (!plant.isDead()) {
                plant.grow();
                PlantEntity updatedPlant = plantRepository.save(plant);
                this.sendLog(PlantMapper.toPlantUpdatedLog(updatedPlant));
            }
        }
    }


    private boolean plantNeedsNourishmentForStat(PlantEntity plant, PlantStat plantStat) {
        return (!plant.isDead() && plant.getRemainingTicksOfHealthyFor(plantStat) < 3);
    }

    private void sendLog(LogMessage message) {
        producerTemplate.sendBody("direct:logQueue", message);
    }

    private void sendPlantsToSellDaily(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        producerTemplate.sendBodyAndHeader("direct:plantQueue", plantDTO, "dead", plant.isDead());
        ;


    }

    private void requestResource(StatType resourceType, int quantity) throws ResourceRequestDeniedException {
        ResourceRequest request = new ResourceRequest(resourceType, quantity, OperationTag.STOCK_QUERIED);
        Response response = stockService.requestResource(request);
        if (response.getStatus() != 200) {
            throw new ResourceRequestDeniedException("Not enough resources available");
        }
    }
}
