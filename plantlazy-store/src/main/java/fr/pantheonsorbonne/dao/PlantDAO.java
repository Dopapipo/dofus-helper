package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;

import java.util.List;
import java.util.UUID;

public interface PlantDAO {
    List<PlantEntity> getAllPlants();

    void savePlant(PlantEntity plant);

    void deletePlantById(UUID plantId);
}
