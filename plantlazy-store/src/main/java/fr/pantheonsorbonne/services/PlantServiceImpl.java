package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.dao.PlantDAO;
import fr.pantheonsorbonne.dto.PlantFromFarmDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ApplicationScoped
public class PlantServiceImpl implements PlantService {

    @Inject
    PlantDAO plantDAO;

    @Inject
    @RestClient
    StockClient stockClient;

    @Inject
    NotificationService notificationService;


    private static final Random random = new Random();

    private static final Map<PlantType, Integer> SALE_PROBABILITIES = new HashMap<>();

    static {
        SALE_PROBABILITIES.put(PlantType.CACTUS, 20);
        SALE_PROBABILITIES.put(PlantType.TREE, 10);
        SALE_PROBABILITIES.put(PlantType.FLOWER, 15);
    }


    @Override
    @Transactional
    public void sellPlants() {
        List<PlantEntity> plants = plantDAO.getAllPlants();

        for (PlantEntity plant : plants) {
            int saleProbability = SALE_PROBABILITIES.getOrDefault(plant.getType(), 0);
            int randomValue = random.nextInt(100);

            if (randomValue < saleProbability) {
                stockClient.updateResource(
                        new ResourceUpdateDTO(ResourceType.MONEY, plant.getPrice(), OperationTag.STOCK_RECEIVED)
                );

                notificationService.notifyPlantSold(plant.getId(), plant.getPrice(), plant.getType());
                plantDAO.deletePlantById(plant.getId());

            }
        }
    }

    @Transactional
    public double getSellingPrice(PlantType plantType) {
        return switch (plantType) {
            case CACTUS -> 150;
            case TREE -> 200;
            case FLOWER -> 100;
        };
    }

    @Override
    @Transactional
    public void putPlantInShop(PlantFromFarmDTO plantDTO) {
        double price = getSellingPrice(plantDTO.plantType());

        PlantEntity plant = new PlantEntity(plantDTO.plantType(), price);

        plantDAO.savePlant(plant);


        notificationService.notifyPlantInShop(plant.getId(), plantDTO.plantType(), price);

    }
}
