package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dao.PlantDAO;
import fr.pantheonsorbonne.entity.PlantEntity;

import fr.pantheonsorbonne.exception.InsufficientStockException;
import fr.pantheonsorbonne.exception.PlantNotFoundException;
import fr.pantheonsorbonne.exception.SaleNotCompletedException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ApplicationScoped
public class PlantServiceImpl implements PlantService {

    @Inject
    PlantDAO plantDAO;

    private static final Random random = new Random();

    // Probabilités de vente par type de plante (en pourcentage)
    private static final Map<String, Integer> SALE_PROBABILITIES = new HashMap<>();

    static {
        SALE_PROBABILITIES.put("Tomate", 70);    // 70% de chances de vente
        SALE_PROBABILITIES.put("Courgette", 50); // 50% de chances de vente
        SALE_PROBABILITIES.put("Concombre", 30); // 30% de chances de vente
    }

    @Override
    public List<PlantEntity> getAvailablePlants() {
        return plantDAO.getAllPlants();
    }

    @Override
    @Transactional
    public void sellPlant(String type, int quantity) {
        PlantEntity plant = plantDAO.getPlantByType(type)
                .orElseThrow(() -> new PlantNotFoundException(type));

        // Probabilité de vente
        int probability = SALE_PROBABILITIES.getOrDefault(type, 0); // 0% si le type est inconnu
        int randomValue = random.nextInt(100); // Génère un nombre entre 0 et 99

        if (randomValue >= probability) {
            throw new SaleNotCompletedException(type); // Vente échoue à cause de la probabilité
        }

        // Vérifier la quantité disponible
        if (plant.getQuantity() < quantity) {
            throw new InsufficientStockException(type, plant.getQuantity(), quantity);
        }

        // Réduction de la quantité en stock
        plant.setQuantity(plant.getQuantity() - quantity);
        plantDAO.updatePlant(plant);

        // Appeler le microservice Stock pour transférer l'argent
    }

    @PostConstruct
    @Transactional
    public void initializePlants() {
        if (plantDAO.getAllPlants().isEmpty()) {
            plantDAO.savePlant(new PlantEntity("Tomate", 100, 0));
            plantDAO.savePlant(new PlantEntity("Courgette", 150, 0));
            plantDAO.savePlant(new PlantEntity("Concombre", 120, 0));
        }
    }

}
