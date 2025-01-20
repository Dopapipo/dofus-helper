package fr.pantheonsorbonne.entity.plant.stat;


import fr.pantheonsorbonne.entity.plant.PlantType;

public interface PlantStat {
    boolean isHealthy();
    boolean isDead();
    void tick();
    int getRemainingTicksOfHealthy();
    int getOptimalRessourceQuantityToFeed();
    StatType getType();
    void feed(int quantity);
}
