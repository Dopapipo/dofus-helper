package fr.pantheonsorbonne.model;

import java.util.HashMap;
import java.util.Map;

public class Dashboard {

    private int day = 1; // Jour actuel
    private int tick = 0; // Tick actuel dans la journée
    private final Map<String, PlantData> plantsInProgress = new HashMap<>(); // Plantes en cours
    private int deadPlantsCount = 0; // Nombre de plantes mortes
    private final Map<String, PlantData> plantsForSale = new HashMap<>(); // Plantes en vente
    private final ResourceData resources = new ResourceData(); // Ressources globales

    // Méthode pour incrémenter le tick
    public void incrementTick() {
        tick++;
        if (tick > 10) {
            tick = 1;
            day++;
        }
    }

    // Méthode pour mettre à jour les ressources
    public void updateResources(int water, int energy, int fertilizer, int money) {
        resources.setWater(water);
        resources.setEnergy(energy);
        resources.setFertilizer(fertilizer);
        resources.setMoney(money);
    }

    // Méthode pour mettre à jour une plante
    public void updatePlant(String plantId, String name, int watering, int energy, int fertilizer, int health) {
        PlantData plant = plantsInProgress.computeIfAbsent(plantId, id -> new PlantData());
        plant.setName(name);
        plant.setWateringLevel(watering);
        plant.setEnergyLevel(energy);
        plant.setFertilizerLevel(fertilizer);
        plant.setHealthLevel(health);
    }

    // Méthode pour ajouter une plante en vente
    public void addPlantForSale(String plantId, String name, int price, int salesRate) {
        PlantData plant = new PlantData();
        plant.setName(name);
        plant.setPrice(price);
        plant.setSalesRate(salesRate);
        plantsForSale.put(plantId, plant);
    }

    // Méthode pour marquer une plante comme morte
    public void incrementDeadPlants(String plantId) {
        plantsInProgress.remove(plantId);
        deadPlantsCount++;
    }

    // Méthode pour afficher le tableau de bord dans la console
    public void display() {
        System.out.println("\n================== Tableau de bord ==================");
        System.out.println("Jour : " + day + " | Tick : " + tick);
        System.out.println("Plantes en cours :");
        plantsInProgress.values().forEach(plant -> System.out.println(" - " + plant));
        System.out.println("Nombre de plantes mortes : " + deadPlantsCount);
        System.out.println("Plantes en vente :");
        plantsForSale.values().forEach(plant -> System.out.println(" - " + plant));
        System.out.println("Ressources : " + resources);
        System.out.println("=====================================================\n");
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public Map<String, PlantData> getPlantsInProgress() {
        return plantsInProgress;
    }

    public int getDeadPlantsCount() {
        return deadPlantsCount;
    }

    public void setDeadPlantsCount(int deadPlantsCount) {
        this.deadPlantsCount = deadPlantsCount;
    }

    public Map<String, PlantData> getPlantsForSale() {
        return plantsForSale;
    }

    public ResourceData getResources() {
        return resources;
    }
}
