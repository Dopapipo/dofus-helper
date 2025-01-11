package fr.pantheonsorbonne.camel.processors.plant;

import fr.pantheonsorbonne.camel.processors.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.camel.processors.plant.stat.PlantStat;
import fr.pantheonsorbonne.camel.processors.seeds.Seed;

public class Plant {
    private PlantType type;
    private double nutrimentConversionRate;
    private PlantStat water;
    private PlantStat sun;
    private PlantStat soil;

    public Plant(PlantType type, PlantStat water, PlantStat sun, PlantStat soil) {
        this.type = type;
        this.water = water;
        this.sun = sun;
        this.soil = soil;
        switch (type) {
            case FLOWER:
                this.nutrimentConversionRate = 0.70;
                break;
            case CACTUS:
                this.nutrimentConversionRate = 0.5;
                break;
            case TREE:
                this.nutrimentConversionRate = 0.25;
                break;
        }
    }

    public Plant(PlantType type, FullPlantStats stats) {
        this(type, stats.getWaterStat(), stats.getSunStat(), stats.getSoilStat());
    }
    public PlantType getType() {
        return type;
    }

    public PlantStat getWater() {
        return water;
    }
    public void tick() {
        this.water.tick();
        this.sun.tick();
        this.soil.tick();
    }
    public void giveWater(int value) {
        this.giveNutriment(water, value);
    }
    public void giveSun(int value) {
        this.giveNutriment(sun, value);
    }

    public void giveSoil(int value){
        this.giveNutriment(soil, value);
    }

    private void giveNutriment(PlantStat stat, int nutrimentQuantity) {
        stat.giveNutriment(nutrimentQuantity, nutrimentConversionRate);
    }
    static Plant fromSeed(Seed seed) {
        return seed.grow();
    }
}
