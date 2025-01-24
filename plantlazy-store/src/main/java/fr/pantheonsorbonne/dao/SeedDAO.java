package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.SeedEntity;

import java.util.List;

public interface SeedDAO {
    List<SeedEntity> getAllSeeds();

    void saveSeed(SeedEntity seed);

    void deleteSeed(SeedEntity seed);

    void deleteAllSeeds();
}
