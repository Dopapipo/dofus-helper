package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.entity.PlantEntity;

public interface PlantManager {
    void sendSoilFromDeadPlants(Iterable<PlantEntity> plants);
    void triggerPlantNourishment(Iterable<PlantEntity> plants);
}
