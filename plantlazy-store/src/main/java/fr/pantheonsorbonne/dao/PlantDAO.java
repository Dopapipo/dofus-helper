package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlantDAO {
    List<PlantEntity> getAllPlants();
    void savePlant(PlantEntity plant);
    void deletePlantById(UUID plantId); // Assurez-vous que cette méthode est bien déclarée ici
}
