package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.camel.client.ResourceStockClient;
import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.exception.ResourceRequestDeniedException;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Queue;
import java.util.List;

@ApplicationScoped
public class PlantService {
    @Inject
    PlantRepository plantRepository;
    @Inject
    ConnectionFactory connectionFactory;

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
        JMSContext context = null;
        try {
            context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();

            Queue queue = context.createQueue("direct:plantQueue");

            for (PlantEntity plant : plants) {
                try {
                    ObjectMessage message = context.createObjectMessage(PlantMapper.toPlantDTO(plant));

                    if (plant.isDead() && !plant.getComposted()) {
                        message.setBooleanProperty("dead", true);
                        producer.send(queue, message);
                        logService.sendLogPlantDiedOrSold(PlantMapper.toPlantSoldLog(plant));

                        System.out.println("plant morte lets go");

                        plant.setComposted(true);

                    } else if (!plant.isDead() && plant.isMature() && !plant.isSold()) {
                        message.setBooleanProperty("sold", true);
                        producer.send(queue, message);
                        logService.sendLogPlantDiedOrSold(PlantMapper.toPlantSoldLog(plant));

                        System.out.println("plant veude lets go");


                        plant.setSold(true);

                    }


                } catch (Exception e) {
                    System.err.println("Failed to process plant with ID " + plant.getId() + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to send plants: " + e.getMessage());
        } finally {
            if (context != null) {
                context.close();
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
        return (!plant.isDead() && plant.getRemainingTicksOfHealthyFor(plantStat) < 3);
    }

}
