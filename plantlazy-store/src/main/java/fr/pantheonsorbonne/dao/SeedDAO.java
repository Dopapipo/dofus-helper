package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.List;
import java.util.Optional;

public interface SeedDAO {
    List<SeedEntity> getAllSeeds();

    void saveSeed(SeedEntity seed);

    void deleteSeed(SeedEntity seed);

    void deleteAllSeeds();
}
