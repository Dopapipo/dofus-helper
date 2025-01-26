package fr.pantheonsorbonne.dto.log;

import java.util.UUID;

public class PlantCreatedLogDTO extends LogDTO{

    private UUID plantId;
    private String name;
    private int energyLevel;
    private int waterLevel;
    private int fertilizerLevel;
    private int growthLevel;

    public PlantCreatedLogDTO() {
    }


    public UUID getPlantId() {
        return plantId;
    }

    public void setPlantId(UUID plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }

    public int getGrowthLevel() {
        return growthLevel;
    }

    public void setGrowthLevel(int growthLevel) {
        this.growthLevel = growthLevel;
    }
}
