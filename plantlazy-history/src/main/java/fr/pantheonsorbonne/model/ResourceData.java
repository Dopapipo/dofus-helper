package fr.pantheonsorbonne.model;

public class ResourceData {
    private int water;
    private int energy;
    private int fertilizer;
    private int money;

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(int fertilizer) {
        this.fertilizer = fertilizer;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ResourceData{" +
                "water=" + water +
                ", energy=" + energy +
                ", fertilizer=" + fertilizer +
                ", money=" + money +
                '}';
    }
}
