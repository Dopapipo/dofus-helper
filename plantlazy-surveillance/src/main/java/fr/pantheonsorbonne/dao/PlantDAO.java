package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;

import java.util.List;
import java.util.Optional;

public interface PlantDAO {
    List<PlantEntity> getAllPlants();

    Optional<PlantEntity> getPlantByType(String type);

    void updatePlant(PlantEntity plant);

    void savePlant(PlantEntity plant);
}
