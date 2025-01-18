package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.SeedRepository;
import fr.pantheonsorbonne.entity.SeedEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SeedService {
    private SeedRepository seedRepository;
    public void saveSeed(SeedEntity seed) {
        seedRepository.save(seed);
    }
}
