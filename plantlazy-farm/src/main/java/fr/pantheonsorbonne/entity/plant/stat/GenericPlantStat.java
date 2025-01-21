package fr.pantheonsorbonne.entity.plant.stat;
// REGLES METIER : si une stat arrive a 0, mort automatique; si une stat arrive a threshold, plante en bonne sante


public class GenericPlantStat implements PlantStat {
    private int value;
    private int threshold;
    private int MAX_VALUE = 100;
    private int decayRate = 2;
    private StatType statType;

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

    public GenericPlantStat(int value, int threshold, StatType statType) {
        this.value = value;
        this.threshold = threshold;
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
    }

    @Override
    public void feed(int quantity) {
        this.increase(quantity);
    }

    @Override
    public void tick() {
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


    public StatType getStatType() {
        return statType;
    }

    public void setStatType(StatType statType) {
        this.statType = statType;
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

        return value == that.value &&
                threshold == that.threshold &&
                MAX_VALUE == that.MAX_VALUE &&
                decayRate == that.decayRate &&
                statType == that.statType;
    }

}
