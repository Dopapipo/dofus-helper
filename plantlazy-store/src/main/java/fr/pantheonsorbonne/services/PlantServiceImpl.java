package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.dao.PlantDAO;
import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
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


    private static final Random random = new Random();

    private static final Map<PlantType, Integer> SALE_PROBABILITIES = new HashMap<>();

    static {
        SALE_PROBABILITIES.put(PlantType.CACTUS, 70);
        SALE_PROBABILITIES.put(PlantType.TREE, 50);
        SALE_PROBABILITIES.put(PlantType.FLOWER, 30);
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
                        new ResourceUpdateDTO(ResourceType.MONEY, plant.getPrice(), PlantType.OperationTag.STOCK_QUERIED)
                );

                plantDAO.deletePlantById(plant.getId());

                System.out.println("La plante de type " + plant.getType() + " n'a a été vendue (probabilité réussie).");


            } else {
                System.out.println("La plante de type " + plant.getType() + " n'a pas été vendue (probabilité échouée).");
            }
        }
    }


    @Transactional
    public double getSellingPrice(PlantType plantType) {
        return switch (plantType) {
            case CACTUS -> 80;
            case TREE -> 100;
            case FLOWER -> 60;
        };
    }

    @Override
    @Transactional
    public void savePlant(PlantSaleDTO plantDTO) {
        double price = getSellingPrice(plantDTO.getPlantType());

        PlantEntity plant = new PlantEntity(plantDTO.getPlantType(), price);

        plantDAO.savePlant(plant);
    }

}
