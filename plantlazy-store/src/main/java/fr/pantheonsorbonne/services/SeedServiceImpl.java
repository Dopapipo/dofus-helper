package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dao.SeedDAO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.entity.SeedEntity;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
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
        int numberOfSeedsToGenerate = 10 + random.nextInt(21); // Génère un nombre aléatoire entre 10 et 30
        for (int i = 0; i < numberOfSeedsToGenerate; i++) {
            double dailyPrice = 20 + (30 * random.nextDouble()); // [20, 50]
            SeedQuality dailyQuality = generateRandomSeedQuality();
            PlantType dailyPlantType = generateRandomPlantType();

            SeedEntity seed = new SeedEntity(); // Crée une nouvelle graine
            seed.setPrice(Math.round(dailyPrice * 100.0) / 100.0); // Arrondi à 2 décimales
            seed.setQuality(dailyQuality); // Qualité aléatoire
            seed.setType(dailyPlantType); // Type aléatoire

            seedDAO.saveSeed(seed); // Sauvegarde la nouvelle graine
        }
    }


    private PlantType generateRandomPlantType() {
        PlantType[] types = PlantType.values();
        int index = random.nextInt(types.length);
        return types[index];
    }

    private SeedQuality generateRandomSeedQuality() {
        SeedQuality[] qualities = SeedQuality.values(); // Récupère toutes les valeurs de l'enum
        int randomIndex = random.nextInt(qualities.length); // Sélectionne un index aléatoire
        return qualities[randomIndex];
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
                .map(seed -> new PurchaseRequestDTO(seed.getType(), seed.getQuality(), seed.getPrice()))
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public double getPriceForTypeAndQuantity(PlantType seedType, int quantity) {
        SeedEntity seed = seedDAO.getSeedByType(seedType).orElseThrow(RuntimeException::new);
        return (seed.getPrice() * quantity);
    }


    @Override
    @Transactional
    public void sellSeed(PlantType seedType, int quantity) {
        // Récupérer toutes les graines du type demandé, triées par prix croissant
        List<SeedEntity> seeds = seedDAO.getAllSeeds().stream()
                .filter(seed -> seed.getType() == seedType)
                .sorted(Comparator.comparingDouble(SeedEntity::getPrice))
                .toList();

        if (seeds.isEmpty()) {
            throw new InsufficientStockException(seedType, 0, quantity); // Pas de stock disponible
        }

        int remainingQuantity = quantity;

        for (SeedEntity seed : seeds) {
            // Supprimer la graine car chaque graine est vendue individuellement
            seedDAO.deleteSeed(seed);

            remainingQuantity--;

            if (remainingQuantity == 0) {
                break; // Terminer si la quantité demandée est atteinte
            }
        }

        // Vérifier s'il reste des quantités non satisfaites
        if (remainingQuantity > 0) {
            throw new InsufficientStockException(seedType, quantity - remainingQuantity, quantity);
        }
    }


    // Initialisation des graines avec des données par défaut au démarrage
    @PostConstruct
    @Transactional
    public void initializeSeedData() {
        if (seedDAO.getAllSeeds().isEmpty()) {
            seedDAO.saveSeed(new SeedEntity(PlantType.CACTUS, 50, 0, SeedQuality.HIGH ));
            seedDAO.saveSeed(new SeedEntity(PlantType.FLOWER, 75, 0, SeedQuality.HIGH));
            seedDAO.saveSeed(new SeedEntity(PlantType.TREE, 60, 0, SeedQuality.HIGH));
        }
    }
}
