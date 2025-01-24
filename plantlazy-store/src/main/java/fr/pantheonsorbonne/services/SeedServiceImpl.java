package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dao.SeedDAO;
import fr.pantheonsorbonne.entity.SeedEntity;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Random;

@ApplicationScoped
public class SeedServiceImpl implements SeedService {

    @Inject
    SeedDAO seedDAO;

    private static final Random random = new Random();

    @Override
    public List<SeedEntity> getAvailableSeeds() {
        return seedDAO.getAllSeeds();
    }

    @Override
    @Transactional
    public void updateDailySeedOffer() {
        List<SeedEntity> seeds = seedDAO.getAllSeeds();
        for (SeedEntity seed : seeds) {
            int dailyQuantity = 2 + random.nextInt(4); // [2, 5]
            seed.setQuantity(dailyQuantity);
            seedDAO.updateSeed(seed);
        }
    }

    @Override
    @Transactional
    public void sellSeed(String seedType, int quantity) {
        SeedEntity seed = seedDAO.getSeedByType(seedType)
                .orElseThrow(() -> new IllegalArgumentException("Seed type not found: " + seedType));

        if (seed.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for seed type: " + seedType);
        }

        seed.setQuantity(seed.getQuantity() - quantity);
        seedDAO.updateSeed(seed);
    }

    // Initialisation des graines avec des données par défaut au démarrage
    @PostConstruct
    @Transactional
    public void initializeSeedData() {
        if (seedDAO.getAllSeeds().isEmpty()) {
            seedDAO.saveSeed(new SeedEntity("Tomate", 50, 0));
            seedDAO.saveSeed(new SeedEntity("Courgette", 75, 0));
            seedDAO.saveSeed(new SeedEntity("Concombre", 60, 0));
        }
    }
}
