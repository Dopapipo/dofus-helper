package fr.pantheonsorbonne.model;

import fr.pantheonsorbonne.dto.DeadPlantDTO;
import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.dto.PlantOfferDTO;
import fr.pantheonsorbonne.dto.SeedOfferDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard {

    private int day = 0;
    private int tick = 0;


    private final Map<String, PlantData> plantsInProgress = new HashMap<>(); // Plantes en cours
    private final Map<String, ResourceData> resources = new HashMap<>(); // Ressources
    private final Map<String, PlantData> seedsForSale = new HashMap<>(); // Graines en vente
    private final Map<String, PlantData> plantsForSale = new HashMap<>(); // Plantes en vente
    private final Map<String, String> deadPlants = new HashMap<>(); // Plantes mortes



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
            System.err.println("Type de ressource non reconu : " + resourceType);
        }
    }



    public void updateSeedsForSale(List<SeedOfferDTO> seeds) {
        seedsForSale.clear();
        for (SeedOfferDTO seed : seeds) {
            PlantData plant = new PlantData();
            plant.setName(seed.getSeedType());
            plant.setPrice((int) seed.getPrice());
            plant.setQuantity(seed.getQuantity());
            seedsForSale.put(seed.getSeedType(), plant);
        }
    }

    public void updatePlantsForSale(List<PlantOfferDTO> plants) {
        plantsForSale.clear();
        for (PlantOfferDTO plant : plants) {
            PlantData plantData = new PlantData();
            plantData.setName(plant.getPlantType());
            plantData.setPrice((int) plant.getSellingPrice());
            plantData.setQuantity(plant.getQuantity());
            plantsForSale.put(plant.getPlantType(), plantData);
        }
    }



    public void updateDeadPlants(List<DeadPlantDTO> deadPlantsList) {
        deadPlants.clear();
        for (DeadPlantDTO deadPlant : deadPlantsList) {
            deadPlants.put(deadPlant.getId(), deadPlant.getName());
        }
    }



    public void updatePlantStats(String plantId, int energy, int fertilizer, int water, int growth) {
        PlantData plant = plantsInProgress.get(plantId);
        if (plant != null) {
            plant.setEnergyLevel(energy);
            plant.setFertilizerLevel(fertilizer);
            plant.setWaterLevel(water);
            plant.setGrowthLevel(growth);
        } else {
            System.err.println("❌ Plante introuvable : " + plantId);
        }
    }

    public void markPlantAsDead(String plantId) {
        PlantData plant = plantsInProgress.remove(plantId);
        if (plant == null) {
            System.err.println("❌ Impossible de marquer comme morte une plante introuvable : " + plantId);
        }
    }

    public void markPlantAsGrown(String plantId) {
        PlantData plant = plantsInProgress.remove(plantId);
        if (plant == null) {
            System.err.println("❌ Impossible de marquer comme mature une plante introuvable : " + plantId);
        }
    }

    public void addNewPlant(String plantId, String plantName, int energy, int fertilizer, int water, int growth) {
        PlantData newPlant = new PlantData();
        newPlant.setId(plantId);
        newPlant.setName(plantName);
        newPlant.setEnergyLevel(energy);
        newPlant.setFertilizerLevel(fertilizer);
        newPlant.setWaterLevel(water);
        newPlant.setGrowthLevel(growth);
        plantsInProgress.put(plantId, newPlant);
    }


    public void updateTick(String tickType) {
        if ("HOURLY".equals(tickType)) {
            tick++;
        }
        else if ("DAILY".equals(tickType)) {
            day++;
            tick = 0;
        }
        else {
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
                            plant.getName(), plant.getWaterLevel(), plant.getEnergyLevel(), plant.getFertilizerLevel()));
        }

        System.out.println("\n💀 Plantes au compost :");
        if (deadPlants.isEmpty()) {
            System.out.println("   Pas de plantes au compost.");
        } else {
            deadPlants.forEach((id, name) ->
                    System.out.printf("   - %s%n", name));
        }

        System.out.println("\n🛒 Plantes en vente :");
        if (plantsForSale.isEmpty()) {
            System.out.println("   Pas de plantes en vente.");
        } else {
            plantsForSale.values().forEach(plant ->
                    System.out.printf("   - %s | 💵 Prix : %d | 📦 Quantité : %d%n",
                            plant.getName(), plant.getPrice(), plant.getQuantity()));
        }

        System.out.println("\n🌱 Graines en vente :");
        if (seedsForSale.isEmpty()) {
            System.out.println("   Pas de graines en vente.");
        } else {
            seedsForSale.values().forEach(seed ->
                    System.out.printf("   - %s | 💵 Prix : %d | 📦 Quantité : %d%n",
                            seed.getName(), seed.getQuantity(), seed.getPrice()));
        }

        System.out.println("\n⚙️ Ressources :");
        if (resources.isEmpty()) {
            System.out.println("   Pas de ressources disponibles.");
        } else {
            resources.values().forEach(resource ->
                    System.out.printf("   - %s : %d%n", resource.getResourceType(), resource.getCurrentValue()));
        }

        System.out.println("=====================================================\n");
    }
}
