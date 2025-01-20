package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dao.SeedDAO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.entity.SeedEntity;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.exception.InsufficientStockException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
            double dailyPrice = 20 + (30 * random.nextDouble()); // [20, 50]

            seed.setQuantity(dailyQuantity);
            seed.setPrice(Math.round(dailyPrice * 100.0) / 100.0); // Arrondi à 2 décimales
            seedDAO.updateSeed(seed);
        }
    }

    @Override
    @Transactional
    public SeedEntity getCheapestSeed() {
        return seedDAO.getAllSeeds().stream()
                .min(Comparator.comparingDouble(SeedEntity::getPrice))
                .orElseThrow(() -> new IllegalArgumentException("No seeds available"));
    }

    @Override
    @Transactional
    public List<PurchaseRequestDTO> getSeedPricingAndStock() {
        return seedDAO.getAllSeeds().stream()
                .map(seed -> new PurchaseRequestDTO(seed.getType(), seed.getQuantity(), seed.getPrice()))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void sellSeed(PlantType seedType, int quantity) {
        List<SeedEntity> seeds = seedDAO.getAllSeeds().stream()
                .sorted(Comparator.comparingDouble(SeedEntity::getPrice)) // Trier par prix croissant
                .toList();

        // Vérifiez s'il n'y a plus de graines disponibles du tout
        int totalStock = seeds.stream().mapToInt(SeedEntity::getQuantity).sum();
        if (totalStock == 0) {
            throw new InsufficientStockException(seedType, 0, quantity);
        }

        int remainingQuantity = quantity;

        for (SeedEntity seed : seeds) {
            if (seed.getQuantity() > 0) {
                int sellableQuantity = Math.min(seed.getQuantity(), remainingQuantity);
                seed.setQuantity(seed.getQuantity() - sellableQuantity);
                seedDAO.updateSeed(seed);

                remainingQuantity -= sellableQuantity;

                if (remainingQuantity == 0) {
                    break; // Terminer si la quantité demandée est atteinte
                }
            }
        }

        // Si la quantité demandée n'est pas totalement satisfaite
        if (remainingQuantity > 0) {
            int availableStock = quantity - remainingQuantity; // Ce qui a été vendu
            throw new InsufficientStockException(seedType, availableStock, quantity);
        }
    }

    // Initialisation des graines avec des données par défaut au démarrage
    @PostConstruct
    @Transactional
    public void initializeSeedData() {
        if (seedDAO.getAllSeeds().isEmpty()) {
            seedDAO.saveSeed(new SeedEntity(PlantType.CACTUS, 50, 0));
            seedDAO.saveSeed(new SeedEntity(PlantType.FLOWER, 75, 0));
            seedDAO.saveSeed(new SeedEntity(PlantType.TREE, 60, 0));
        }
    }
}
