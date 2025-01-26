package fr.pantheonsorbonne.entity.plant.stat;
// REGLES METIER : si une stat arrive a 0, mort automatique; si une stat arrive a threshold, plante en bonne sante

import jakarta.persistence.MappedSuperclass;

import java.io.Serial;
import java.io.Serializable;

@MappedSuperclass
public abstract class GenericPlantStat implements PlantStat, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private int value;
    private int threshold;
    private final int MAX_VALUE = 100;
    private int decayRate = 7;
    private StatType statType;

    public int getValue() {
        return value;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getDecayRate() {
        return decayRate;
    }

    public GenericPlantStat(int value, int threshold, int decayRate, StatType statType) {
        this.value = value;
        this.threshold = threshold;
        this.decayRate = decayRate;
        this.statType = statType;
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
        System.out.println("decay : " + value);

    }

    @Override
    public void feed(int quantity) {
        this.increase(quantity);
    }

    @Override
    public void tick() {
        System.out.println("decay : " + decayRate);
        this.decrease(decayRate);
    }

    @Override
    public int getRemainingTicksOfHealthy() {
        return Math.max((this.value - this.threshold) / this.decayRate, 0);
    }

    // 2 ticks above threshold
    @Override
    public int getOptimalRessourceQuantityToFeed() {
        return Math.max((this.threshold + 2 * this.decayRate) - this.value, 0);
    }

    @Override
    public StatType getType() {
        return this.statType;
    }

    @Override
    public String toString() {
        return "GenericPlantStat{" +
                "value=" + this.value +
                ", threshold=" + threshold +
                ", MAX_VALUE=" + MAX_VALUE +
                ", decayRate=" + decayRate +
                ", statType=" + this.statType +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GenericPlantStat that = (GenericPlantStat) obj;

        return value == that.value && threshold == that.threshold && decayRate == that.decayRate && statType == that.statType;
    }

}
