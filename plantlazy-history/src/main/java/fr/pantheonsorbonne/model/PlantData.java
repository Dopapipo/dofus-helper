package fr.pantheonsorbonne.model;

public class PlantData {
    private String name;
    private int wateringLevel;
    private int energyLevel;
    private int fertilizerLevel;
    private int healthLevel;
    private int price;
    private int salesRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWateringLevel() {
        return wateringLevel;
    }

    public void setWateringLevel(int wateringLevel) {
        this.wateringLevel = wateringLevel;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalesRate() {
        return salesRate;
    }

    public void setSalesRate(int salesRate) {
        this.salesRate = salesRate;
    }

    @Override
    public String toString() {
        return "PlantData{" +
                "name='" + name + '\'' +
                ", wateringLevel=" + wateringLevel +
                ", energyLevel=" + energyLevel +
                ", fertilizerLevel=" + fertilizerLevel +
                ", healthLevel=" + healthLevel +
                ", price=" + price +
                ", salesRate=" + salesRate +
                '}';
    }
}
