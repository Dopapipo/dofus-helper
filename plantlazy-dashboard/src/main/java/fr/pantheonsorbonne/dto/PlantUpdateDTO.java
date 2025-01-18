package fr.pantheonsorbonne.dto;

public class PlantUpdateDTO {
    private String plantId;
    private String plantName;
    private int updatedWateringLevel;
    private int updatedEnergyLevel;
    private int updatedFertilizerLevel;
    private int updatedHealthLevel;

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getUpdatedWateringLevel() {
        return updatedWateringLevel;
    }

    public void setUpdatedWateringLevel(int updatedWateringLevel) {
        this.updatedWateringLevel = updatedWateringLevel;
    }

    public int getUpdatedEnergyLevel() {
        return updatedEnergyLevel;
    }

    public void setUpdatedEnergyLevel(int updatedEnergyLevel) {
        this.updatedEnergyLevel = updatedEnergyLevel;
    }

    public int getUpdatedFertilizerLevel() {
        return updatedFertilizerLevel;
    }

    public void setUpdatedFertilizerLevel(int updatedFertilizerLevel) {
        this.updatedFertilizerLevel = updatedFertilizerLevel;
    }

    public int getUpdatedHealthLevel() {
        return updatedHealthLevel;
    }

    public void setUpdatedHealthLevel(int updatedHealthLevel) {
        this.updatedHealthLevel = updatedHealthLevel;
    }
}
