package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.List;

public interface SeedService {
    List<SeedEntity> getAvailableSeeds();
    void updateDailySeedOffer();
    void sellSeed(PlantType seedType, int quantity);
}
