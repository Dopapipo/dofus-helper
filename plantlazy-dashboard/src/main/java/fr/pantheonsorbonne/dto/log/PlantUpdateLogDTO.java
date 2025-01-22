package fr.pantheonsorbonne.dto.log;

public class PlantUpdateLogDTO extends LogDTO {

    private String plantId;
    private int energyLevel;
    private int waterLevel;
    private int fertilizerLevel;
    private int growthLevel;

    public PlantUpdateLogDTO() {
    }


    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
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
