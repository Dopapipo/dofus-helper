package fr.pantheonsorbonne.model;

import fr.pantheonsorbonne.dto.SeedDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Dashboard {

    private int day = 0;
    private int tick = 0;

    private final Map<UUID, PlantData> plantsInProgress = new HashMap<>();
    private final Map<String, ResourceData> resources = new HashMap<>();
    private final Map<String, PlantData> seedsForSale = new HashMap<>();
    private final Map<UUID, PlantData> plantsForSale = new HashMap<>();


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
            plant.setName(seed.getType());
            plant.setPrice(seed.getPrice());
            plant.setQuantity(1);
            seedsForSale.put(seed.getId(), plant);
        }

    }


    public void updatePlantsForSale(UUID plantId, String name, int price) {
        PlantData plant = new PlantData();
        plant.setName(name);
        plant.setPrice(price);
        plantsForSale.put(plantId, plant);
    }

    public void updateSoldPlants(UUID plantId, int price) {
        plantsForSale.remove(plantId);
    }


    public boolean removePlant(UUID plantId) {
        if (plantsInProgress.containsKey(plantId)) {
            plantsInProgress.remove(plantId);
            return true;
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


    public void markPlantAsMature(UUID plantId) {
        PlantData plant = plantsInProgress.get(plantId);
        if (plant != null) {
            plant.setGrowthLevel(100); // Marque comme mûr
        }
    }

    public void updatePlantStats(UUID plantId, int energyLevel, int waterLevel, int fertilizerLevel) {
        PlantData plant = plantsInProgress.get(plantId);
        if (plant != null) {
            plant.setEnergyLevel(energyLevel);
            plant.setWaterLevel(waterLevel);
            plant.setFertilizerLevel(fertilizerLevel);
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
                    System.out.printf("   - %s | 💵 Prix : %d%n",
                            seed.getName(), seed.getPrice()));
        }

        System.out.println("\n⚙️ Ressources :");
        resources.values().forEach(resource ->
                System.out.printf("   - %s : %d%n", resource.getResourceType(), resource.getCurrentValue()));

        System.out.println("=====================================================\n");
    }
}
