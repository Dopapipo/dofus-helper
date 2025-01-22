package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.dao.PlantDAO;
import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.PlantEntity;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.exception.InsufficientStockException;
import fr.pantheonsorbonne.exception.PlantNotFoundException;
import fr.pantheonsorbonne.exception.SaleNotCompletedException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
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
    SeedNotificationService seedNotificationService;


    private static final Random random = new Random();

    private static final Map<PlantType, Integer> SALE_PROBABILITIES = new HashMap<>();

    static {
        SALE_PROBABILITIES.put(PlantType.CACTUS, 70);    // 70% de chances de vente
        SALE_PROBABILITIES.put(PlantType.TREE, 50); // 50% de chances de vente
        SALE_PROBABILITIES.put(PlantType.FLOWER, 30); // 30% de chances de vente
    }

    @Inject
    SendingSeedLogService sendingSeedLogService;

    @Override
    public List<PlantEntity> getAvailablePlants() {
        return plantDAO.getAllPlants();
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

    /**
     * Obtenir le prix de vente basé sur le type de plante.
     */
    @Transactional
    public double getSellingPrice(PlantType plantType) {
        switch (plantType) {
            case CACTUS:
                return 80;
            case TREE:
                return 100;
            case FLOWER:
                return 60;
            default:
                throw new IllegalArgumentException("Type de plante inconnu : " + plantType);
        }
    }

    @Override
    @Transactional
    public void savePlant(PlantSaleDTO plantDTO) {
        // Déterminer le prix de la plante en fonction de son type
        double price = getSellingPrice(plantDTO.getPlantType());

        // Créer une nouvelle entité PlantEntity
        PlantEntity plant = new PlantEntity(plantDTO.getPlantType(), price);

        // Sauvegarder l'entité dans la base de données
        plantDAO.savePlant(plant);
    }

}
