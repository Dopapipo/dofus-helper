package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.SeedEntity;

import java.util.List;
import java.util.Optional;

public interface SeedDAO {
    List<SeedEntity> getAllSeeds();

    Optional<SeedEntity> getSeedByType(String type);

    void updateSeed(SeedEntity seed);

    void saveSeed(SeedEntity seed);
}
