package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;

public interface PlantRessourceManager {
    void feedPlant(PlantEntity plant, int quantity, PlantStat statType);
}
