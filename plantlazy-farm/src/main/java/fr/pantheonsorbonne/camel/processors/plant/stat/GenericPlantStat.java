package fr.pantheonsorbonne.camel.processors.plant.stat;
// REGLES METIER : si une stat arrive a 0, mort automatique; si une stat arrive a threshold, plante en bonne sante

import jakarta.persistence.Embeddable;

@Embeddable
public class GenericPlantStat implements PlantStat {
    private int value;
    private  int threshold;
    private  int MAX_VALUE = 100;
    private int decayRate = 10;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getMAX_VALUE() {
        return MAX_VALUE;
    }

    public void setMAX_VALUE(int MAX_VALUE) {
        this.MAX_VALUE = MAX_VALUE;
    }

    public int getDecayRate() {
        return decayRate;
    }

    public void setDecayRate(int decayRate) {
        this.decayRate = decayRate;
    }

    public GenericPlantStat(int value, int threshold) {
        this.value = value;
        this.threshold = threshold;
    }
    public GenericPlantStat(int value, int threshold, int decayRate) {
        this.value = value;
        this.threshold = threshold;
        this.decayRate = decayRate;
    }

    public GenericPlantStat() {

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
