package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.entity.PlantEntity;

import java.util.List;

public interface PlantService {
    List<PlantEntity> getAvailablePlants();

    void sellPlant(String type, int quantity);
}
