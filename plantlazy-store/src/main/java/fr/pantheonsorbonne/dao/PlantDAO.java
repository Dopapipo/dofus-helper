package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.List;
import java.util.Optional;

public interface PlantDAO {
    List<PlantEntity> getAllPlants();
    Optional<PlantEntity> getPlantByType(PlantType type);
    void updatePlant(PlantEntity plant);
    void savePlant(PlantEntity plant);
    void deletePlantById(long plantId); // Assurez-vous que cette méthode est bien déclarée ici
}
