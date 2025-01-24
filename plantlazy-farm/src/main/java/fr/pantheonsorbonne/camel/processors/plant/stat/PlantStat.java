package fr.pantheonsorbonne.camel.processors.plant.stat;


public interface PlantStat {
    boolean isHealthy();
    boolean isDead();
    void giveNutriment(int value, double nutrimentConversionRate);
    void tick();
}
