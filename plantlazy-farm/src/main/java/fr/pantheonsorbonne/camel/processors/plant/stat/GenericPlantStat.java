package fr.pantheonsorbonne.camel.processors.plant.stat;
// REGLES METIER : si une stat arrive a 0, mort automatique; si une stat arrive a threshold, plante en bonne sante

import fr.pantheonsorbonne.camel.processors.plant.PlantType;

public class GenericPlantStat implements PlantStat {
    private int value;
    private final int threshold;
    private final int MAX_VALUE = 100;
    private int decayRate = 10;

    public GenericPlantStat(int value, int threshold) {
        this.value = value;
        this.threshold = threshold;
    }
    public GenericPlantStat(int value, int threshold, int decayRate) {
        this.value = value;
        this.threshold = threshold;
        this.decayRate = decayRate;
    }
    @Override
    public boolean isHealthy() {
        return this.value >= this.threshold;
    }

    @Override
    public boolean isDead() {
        return this.value == 0;
    }
    protected void increase(int value) {
        this.value = Math.min(this.value + value, MAX_VALUE);
    }
    protected void decrease(int value) {
        this.value = Math.max(this.value - value, 0);
    }

    public void giveNutriment(int quantity, double conversionRate) {
        this.increase((int) (quantity * conversionRate));
    }

    @Override
    public void tick() {
        this.decrease(decayRate);
    }

}
