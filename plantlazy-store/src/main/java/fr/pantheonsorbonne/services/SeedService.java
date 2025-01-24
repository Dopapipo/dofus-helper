package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.entity.SeedEntity;

import java.util.List;

public interface SeedService {
    List<SeedEntity> getAvailableSeeds();

    void updateDailySeedOffer();

    void sellSeedsDaily();


}
