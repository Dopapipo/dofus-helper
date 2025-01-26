package fr.pantheonsorbonne.model;

import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.dto.log.StoreSellableSeedsLogDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Dashboard {

    private int day = 0;
    private int tick = 0;

    private final Map<UUID, PlantData> plantsInProgress = new HashMap<>(); // Plantes en cours
    private final Map<String, ResourceData> resources = new HashMap<>();    // Ressources
    private final Map<String, PlantData> seedsForSale = new HashMap<>();    // Graines en vente
    private final Map<String, PlantData> plantsForSale = new HashMap<>();   // Plantes en vente
    private final Map<String, PlantData> deadPlants = new HashMap<>();      // Plantes mortes

    public Dashboard() {
        resources.put("WATER", new ResourceData("WATER", 0));
        resources.put("ENERGY", new ResourceData("ENERGY", 0));
        resources.put("FERTILIZER", new ResourceData("FERTILIZER", 0));
        resources.put("MONEY", new ResourceData("MONEY", 0));
    }

    public void updateResource(String resourceType, int newValue) {
        ResourceData resource = resources.get(resourceType.toUpperCase());
        if (resource != null) {
            resource.setCurrentValue(newValue);
        } else {
            System.err.println("Type de ressource non reconnu : " + resourceType);
        }
    }

    public void updateSeedsForSale(List<SeedDTO> seeds) {
        seedsForSale.clear();
        for (SeedDTO seed : seeds) {
            PlantData plant = new PlantData();
            plant.setName(seed.getType()); // Utilise le type comme nom de la plante
            plant.setPrice(seed.getPrice());
            plant.setQuantity(1); // Quantité par défaut, car SeedDTO ne contient pas de champ quantity
            seedsForSale.put(seed.getId(), plant);
        }

    }


    public void updatePlantsForSale(String plantId, String name, int price) {
        System.out.println("for sale");
        PlantData plant = new PlantData();
        plant.setName(name);
        plant.setPrice(price);
        plantsForSale.put(plantId, plant);
        System.out.println(plantsForSale);
    }

    public void updateSoldPlants(String plantType, int price) {
        plantsForSale.remove(plantType); // Retire la plante des ventes
        System.out.printf("✔️ Plante vendue : %s pour %d 💵%n", plantType, price);
    }

    public void updateDeadPlant(String plantId, String name, int decompositionLevel) {
        PlantData plant = new PlantData();
        plant.setName(name);
        plant.setDecompositionLevel(decompositionLevel);
        deadPlants.put(plantId, plant);
    }

    public boolean removePlant(UUID plantId) {
        System.out.println("🌱 Current plants in progress: " + plantsInProgress.keySet());
        for (UUID id : plantsInProgress.keySet()) {
            System.out.printf("Comparing received UUID: %s with stored UUID: %s%n", plantId, id);
            if (id.equals(plantId)) {
                plantsInProgress.remove(id);
                return true;
            }
        }
        return false;
    }


    public void addNewPlant(UUID plantId, String name, int energyLevel, int waterLevel, int fertilizerLevel) {
        PlantData plant = new PlantData();
        plant.setName(name);
        plant.setEnergyLevel(energyLevel);
        plant.setWaterLevel(waterLevel);
        plant.setFertilizerLevel(fertilizerLevel);
        plantsInProgress.put(plantId, plant);
    }

    public boolean plantExists(UUID plantId) {
        return plantsInProgress.containsKey(plantId);
    }


    public void markPlantAsMature(String plantId) {
        PlantData plant = plantsInProgress.get(plantId);
        if (plant != null) {
            plant.setGrowthLevel(100); // Marque comme mûr
            System.out.printf("🌟 Plante mature : %s !%n", plant.getName());
        } else {
            System.err.printf("Impossible de trouver la plante %s en cours de croissance.%n", plantId);
        }
    }

    public void updatePlantStats(UUID plantId, int energyLevel, int waterLevel, int fertilizerLevel) {
        PlantData plant = plantsInProgress.get(plantId);
        if (plant != null) {
            plant.setEnergyLevel(energyLevel);
            plant.setWaterLevel(waterLevel);
            plant.setFertilizerLevel(fertilizerLevel);
        } else {
            System.err.printf("Impossible de trouver la plante %s en cours de croissance.%n", plantId);
        }
    }

    public void updateTick(String tickType) {
        if ("HOURLY".equals(tickType)) {
            tick++;
        } else if ("DAILY".equals(tickType)) {
            day++;
            tick = 0;
            display();
        } else {
            System.err.println("Type de tick non reconnu : " + tickType);
        }
    }

    public void display() {
        System.out.println("\n================== 🌱 DASHBOARD 🌱 ==================");

        System.out.printf("📅 Jour : %d | ⏱️ Heure : %d%n", day, tick);

        System.out.println("\n🌿 Plantes en cours :");
        if (plantsInProgress.isEmpty()) {
            System.out.println("   Pas de plantes en cours.");
        } else {
            plantsInProgress.values().forEach(plant ->
                    System.out.printf("   - %s | 💧 Eau : %d | ☀️ Énergie : %d | \uD83C\uDF30 Fertilizer : %d%n",
                            plant.getName(), plant.getWaterLevel(), plant.getEnergyLevel(),
                            plant.getFertilizerLevel()));
        }

            System.out.println("\n💀 Plantes au compost :");
        if (deadPlants.isEmpty()) {
            System.out.println("   Pas de plantes au compost.");
        } else {
            deadPlants.values().forEach(plant ->
                    System.out.printf("   - %s | Niveau de décomposition : %d%%%n",
                            plant.getName(), plant.getDecompositionLevel()));
        }

        System.out.println("\n🛒 Plantes en vente :");
        if (plantsForSale.isEmpty()) {
            System.out.println("   Pas de plantes en vente.");
        } else {
            plantsForSale.values().forEach(plant ->
                    System.out.printf("   - %s | 💵 Prix : %d%n", plant.getName(), plant.getPrice()));
        }

        System.out.println("\n🌱 Graines en vente :");
        if (seedsForSale.isEmpty()) {
            System.out.println("   Pas de graines en vente.");
        } else {
            seedsForSale.values().forEach(seed ->
                    System.out.printf("   - %s | 💵 Prix : %d | 📦 Quantité : %d%n",
                            seed.getName(), seed.getPrice(), seed.getQuantity()));
        }

        System.out.println("\n⚙️ Ressources :");
        resources.values().forEach(resource ->
                System.out.printf("   - %s : %d%n", resource.getResourceType(), resource.getCurrentValue()));

        System.out.println("=====================================================\n");
    }
}
