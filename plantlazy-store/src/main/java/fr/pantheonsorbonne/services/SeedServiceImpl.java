package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dao.SeedDAO;
import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.dto.SeedLogDTO;
import fr.pantheonsorbonne.entity.SeedEntity;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import fr.pantheonsorbonne.exception.InsufficientStockException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@ApplicationScoped
public class SeedServiceImpl implements SeedService {

    @Inject
    SeedDAO seedDAO;

    @Inject
    SeedNotificationService seedNotificationService;

    private static final Random random = new Random();

    @Override
    public List<SeedEntity> getAvailableSeeds() {
        return seedDAO.getAllSeeds();
    }

    @Override
    @Transactional
    public void updateDailySeedOffer() {
        // Supprimer toutes les graines existantes dans la base de données
        seedDAO.deleteAllSeeds();
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
        sendSeedLog();
    }


    private void sendSeedLog() {
        for (PlantType type : PlantType.values()) { // Parcourt tous les types de l'énum PlantType
            seedNotificationService.notifySeedUpdate(type, seedDAO.countSeedsByType(type)); // Appelle la fonction pour chaque type
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
    public double getCalculateTotalPrice(int quantity) {
        // Récupérer toutes les graines, triées par prix croissant
        List<SeedEntity> seeds = seedDAO.getAllSeeds().stream()
                .sorted(Comparator.comparingDouble(SeedEntity::getPrice))
                .toList();

        if (seeds.size() < quantity) {
            throw new InsufficientStockException(quantity); // Pas assez de stock disponible
        }

        // Calculer le prix total des graines les moins chères
        double totalPrice = 0.0;
        for (int i = 0; i < quantity; i++) {
            totalPrice += seeds.get(i).getPrice();
        }

        return totalPrice;
    }


    @Override
    @Transactional
    public List<DailySeedOfferDTO> sellSeed(int quantity) {
        // Récupérer toutes les graines, triées par prix croissant
        List<SeedEntity> seeds = seedDAO.getAllSeeds().stream()
                .sorted(Comparator.comparingDouble(SeedEntity::getPrice))
                .toList();

        if (seeds.size() < quantity) {
            throw new InsufficientStockException(quantity); // Pas assez de stock disponible
        }

        // Préparer la liste des DTO des graines vendues
        List<DailySeedOfferDTO> soldSeeds = new ArrayList<>();

        // Boucle pour supprimer uniquement la quantité demandée des graines les moins chères
        for (int i = 0; i < quantity; i++) {
            SeedEntity seedToSell = seeds.get(i);

            // Convertir l'entité en DTO
            DailySeedOfferDTO dto = new DailySeedOfferDTO(
                    seedToSell.getType(),
                    seedToSell.getQuality(),
                    seedToSell.getPrice()
            );

            soldSeeds.add(dto);

            // Supprimer la graine de la base de données
            seedDAO.deleteSeed(seedToSell);
        }

        // Retourner la liste des graines vendues
        return soldSeeds;
    }




    // Initialisation des graines avec des données par défaut au démarrage
    @PostConstruct
    @Transactional
    public void initializeSeedData() {
        if (seedDAO.getAllSeeds().isEmpty()) {
            seedDAO.saveSeed(new SeedEntity(PlantType.CACTUS, 50, SeedQuality.HIGH ));
            seedDAO.saveSeed(new SeedEntity(PlantType.FLOWER, 75, SeedQuality.HIGH));
            seedDAO.saveSeed(new SeedEntity(PlantType.TREE, 60, SeedQuality.HIGH));
        }
    }
}
