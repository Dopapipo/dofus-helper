package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.List;

public interface PlantService {
    List<PlantEntity> getAvailablePlants();

    void sellPlant(PlantType type, int quantity);
}
