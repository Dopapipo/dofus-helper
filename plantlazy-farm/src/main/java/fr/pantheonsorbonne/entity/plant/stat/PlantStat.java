package fr.pantheonsorbonne.entity.plant.stat;


public interface PlantStat {
    boolean isHealthy();

    boolean isDead();

    void tick();

    int getRemainingTicksOfHealthy();

    int getOptimalRessourceQuantityToFeed();

    StatType getType();

    void feed(int quantity);
}
