package fr.pantheonsorbonne.model;

import fr.pantheonsorbonne.dto.log.StoreSellableSeedsLogDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard {

    private int day = 0;
    private int tick = 0;

    private final Map<String, PlantData> plantsInProgress = new HashMap<>(); // Plantes en cours
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

    public void updateSeedsForSale(List<StoreSellableSeedsLogDTO.Seed> seeds) {
        seedsForSale.clear();
        for (StoreSellableSeedsLogDTO.Seed seed : seeds) {
            PlantData plant = new PlantData();
            plant.setName(seed.getSpecies());
            plant.setPrice(seed.getPrice());
            plant.setQuantity(seed.getQuantity());
            seedsForSale.put(seed.getSeedId(), plant);
        }
    }

    public void updatePlantsForSale(String plantId, String name, int price) {
        PlantData plant = new PlantData();
        plant.setName(name);
        plant.setPrice(price);
        plantsForSale.put(plantId, plant);
    }

    public void updateSoldPlants(String plantId, int price) {
        plantsForSale.remove(plantId); // Retire la plante des ventes
        System.out.printf("✔️ Plante vendue : %s pour %d 💵%n", plantId, price);
    }

    public void updateDeadPlant(String plantId, String name, int decompositionLevel) {
        PlantData plant = new PlantData();
        plant.setName(name);
        plant.setDecompositionLevel(decompositionLevel);
        deadPlants.put(plantId, plant);
    }

    public boolean removePlant(String plantId) {
        if (plantsInProgress.containsKey(plantId)) {
            plantsInProgress.remove(plantId);
            return true;
        }
        return false;
    }

    public void addNewPlant(String plantId, String name, int energyLevel, int waterLevel, int fertilizerLevel, int growthLevel) {
        PlantData plant = new PlantData();
        plant.setName(name);
        plant.setEnergyLevel(energyLevel);
        plant.setWaterLevel(waterLevel);
        plant.setFertilizerLevel(fertilizerLevel);
        plant.setGrowthLevel(growthLevel);
        plantsInProgress.put(plantId, plant);
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

    public void updatePlantStats(String plantId, int energyLevel, int waterLevel, int fertilizerLevel, int growthLevel) {
        PlantData plant = plantsInProgress.get(plantId);
        if (plant != null) {
            plant.setEnergyLevel(energyLevel);
            plant.setWaterLevel(waterLevel);
            plant.setFertilizerLevel(fertilizerLevel);
            plant.setGrowthLevel(growthLevel);
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
                    System.out.printf("   - %s | 💧 Eau : %d | ☀️ Énergie : %d | \uD83C\uDF30 Fertilizer : %d | 📈 Croissance : %d%%%n",
                            plant.getName(), plant.getWaterLevel(), plant.getEnergyLevel(),
                            plant.getFertilizerLevel(), plant.getGrowthLevel()));
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
