package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dao.SeedDAO;
import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import fr.pantheonsorbonne.exception.InsufficientFundsException;
import fr.pantheonsorbonne.exception.InsufficientStockException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@ApplicationScoped
public class SeedServiceImpl implements SeedService {

    @Inject
    @ConfigProperty(name = "plant.seed.endpoint")
    String seedEndpoint;


    @Inject
    SeedDAO seedDAO;

    @Inject
    SeedNotificationService seedNotificationService;

    @Inject
    StoreService storeService;

    @Inject
    ProducerTemplate producerTemplate;


    private static final Random random = new Random();

    @Override
    public List<SeedEntity> getAvailableSeeds() {
        return seedDAO.getAllSeeds();
    }

    @Override
    @Transactional
    public void updateDailySeedOffer() {
        seedDAO.deleteAllSeeds();
        int numberOfSeedsToGenerate = 10 + random.nextInt(21);
        for (int i = 0; i < numberOfSeedsToGenerate; i++) {
            double dailyPrice = 20 + (30 * random.nextDouble());
            SeedQuality dailyQuality = generateRandomSeedQuality();
            PlantType dailyPlantType = generateRandomPlantType();

            SeedEntity seed = new SeedEntity();
            seed.setPrice(Math.round(dailyPrice * 100.0) / 100.0);
            seed.setQuality(dailyQuality);
            seed.setType(dailyPlantType);

            seedDAO.saveSeed(seed);
        }
        sendSeedLog();
    }


    private void sendSeedLog() {
        for (PlantType type : PlantType.values()) {
            seedNotificationService.notifySeedUpdate(type, seedDAO.countSeedsByType(type));
        }
    }

    private PlantType generateRandomPlantType() {
        PlantType[] types = PlantType.values();
        int index = random.nextInt(types.length);
        return types[index];
    }

    private SeedQuality generateRandomSeedQuality() {
        SeedQuality[] qualities = SeedQuality.values();
        int randomIndex = random.nextInt(qualities.length);
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
        List<SeedEntity> seeds = seedDAO.getAllSeeds().stream()
                .sorted(Comparator.comparingDouble(SeedEntity::getPrice))
                .toList();

        if (seeds.size() < quantity) {
            throw new InsufficientStockException(quantity);
        }

        double totalPrice = 0.0;
        for (int i = 0; i < quantity; i++) {
            totalPrice += seeds.get(i).getPrice();
        }

        return totalPrice;
    }


    @Override
    @Transactional
    public void sellSeed() {
        List<SeedEntity> seeds = seedDAO.getAllSeeds().stream()
                .sorted(Comparator.comparingDouble(SeedEntity::getPrice))
                .toList();

        double availableMoney = storeService.getAvailableMoney();

        if (seeds.isEmpty() || seeds.getFirst().getPrice() > availableMoney) {
            throw new InsufficientFundsException("Buying 1 seed is too much for you.");
        }

        for (SeedEntity seed : seeds) {
            if (seed.getPrice() <= availableMoney) {
                availableMoney -= seed.getPrice();

                producerTemplate.sendBody(seedEndpoint, seed);
                seedNotificationService.notifyPlantSale(seed.getType(), seed.getPrice());
                seedDAO.deleteSeed(seed);
            } else {
                break;
            }
        }
    }


    @PostConstruct
    @Transactional
    public void initializeSeedData() {
        if (seedDAO.getAllSeeds().isEmpty()) {
            seedDAO.saveSeed(new SeedEntity(PlantType.CACTUS, 50, SeedQuality.HIGH));
            seedDAO.saveSeed(new SeedEntity(PlantType.FLOWER, 75, SeedQuality.HIGH));
            seedDAO.saveSeed(new SeedEntity(PlantType.TREE, 60, SeedQuality.HIGH));
        }
    }
}
