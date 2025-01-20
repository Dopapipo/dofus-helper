package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.entity.PlantEntity;

public interface PlantManager {
    void sendDeadPlants(Iterable<PlantEntity> plants);
    void triggerPlantNourishment(Iterable<PlantEntity> plants);
    void triggerPlantGrowth(Iterable<PlantEntity> plants);
}
