package fr.pantheonsorbonne.dto;

public class PlantStats {

    private double waterLevel;
    private double sunLevel;
    private double growthRate;

    public PlantStats(double waterLevel, double sunLevel, double growthRate) {
        this.waterLevel = waterLevel;
        this.sunLevel = sunLevel;
        this.growthRate = growthRate;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public double getSunLevel() {
        return sunLevel;
    }

    public void setSunLevel(double sunLevel) {
        this.sunLevel = sunLevel;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    @Override
    public String toString() {
        return "Water Level : " + this.waterLevel + ", Sun Level : " + sunLevel + ", Growth Rate : " + growthRate;
    }
}
