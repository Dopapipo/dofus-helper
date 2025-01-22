package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.dao.SeedDAO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.dto.SeedToFarmDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import fr.pantheonsorbonne.exception.InsufficientFundsException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

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

    @RestClient
    @Inject
    StockClient StockClient;


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
    public List<PurchaseRequestDTO> getSeedPricingAndStock() {
        return seedDAO.getAllSeeds().stream()
                .map(seed -> new PurchaseRequestDTO(seed.getType(), seed.getQuality(), seed.getPrice()))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void sellSeedsDaily() {
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


                producerTemplate.sendBody(seedEndpoint, new SeedToFarmDTO(seed.getType(), seed.getQuality()));
                seedNotificationService.notifyPlantSale(seed.getType(), seed.getPrice());
                seedDAO.deleteSeed(seed);

            } else {
                StockClient.updateResource(new ResourceUpdateDTO(ResourceType.MONEY, storeService.getAvailableMoney() - (storeService.getAvailableMoney() - availableMoney), PlantType.OperationTag.STOCK_QUERIED));
                break;
            }
        }
    }

}
