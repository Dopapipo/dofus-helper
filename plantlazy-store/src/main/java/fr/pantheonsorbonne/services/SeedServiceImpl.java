package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.camel.producer.SeedProducer;
import fr.pantheonsorbonne.dao.SeedDAO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.dto.SeedToFarmDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import fr.pantheonsorbonne.exception.InsufficientFundsException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.*;

@ApplicationScoped
public class SeedServiceImpl implements SeedService {

    @Inject
    SeedDAO seedDAO;

    @Inject
    NotificationService notificationService;

    @Inject
    StoreService storeService;

    @Inject
    SeedProducer SeedProducer;

    @RestClient
    @Inject
    StockClient StockClient;


    private static final Random random = new Random();

    @Override
    @Transactional
    public void updateDailySeedOffer() {
        seedDAO.deleteAllSeeds();

        Map<PlantType, Double> fixedPrices = new HashMap<>();
        for (PlantType plantType : PlantType.values()) {
            double price = 20 + (30 * random.nextDouble());
            fixedPrices.put(plantType, Math.round(price * 100.0) / 100.0); // Arrondi à 2 décimales
        }

        int numberOfSeedsToGenerate = 2 /*+ random.nextInt(1)*/;
        for (int i = 0; i < numberOfSeedsToGenerate; i++) {
            SeedQuality dailyQuality = generateRandomSeedQuality();
            PlantType dailyPlantType = generateRandomPlantType();

            SeedEntity seed = new SeedEntity();
            seed.setPrice(fixedPrices.get(dailyPlantType));
            seed.setQuality(dailyQuality);
            seed.setType(dailyPlantType);

            seedDAO.saveSeed(seed);
        }

        sendSeedLog();

    }

    private void sendSeedLog() {
        List<SeedEntity> allSeeds = seedDAO.getAllSeeds();
        notificationService.notifySeedUpdate(allSeeds);
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
    public void sellSeedsDaily() throws InsufficientFundsException {
        List<SeedEntity> seeds = seedDAO.getAllSeeds().stream()
                .sorted(Comparator.comparingDouble(SeedEntity::getPrice))
                .toList();

        double availableMoney = storeService.getAvailableMoney();

        if (seeds.isEmpty() || seeds.getFirst().getPrice() > availableMoney) {
            throw new InsufficientFundsException("Not enough money to buy them");
        }

        for (SeedEntity seed : seeds) {
            if (seed.getPrice() <= availableMoney) {
                availableMoney -= seed.getPrice();

                SeedToFarmDTO seedDTO = new SeedToFarmDTO(seed.getType(), seed.getQuality());
                SeedProducer.sendSeedMessageToFarm(seedDTO);


                StockClient.updateResource(new ResourceUpdateDTO(ResourceType.MONEY, seed.getPrice(), OperationTag.STOCK_QUERIED));

                seedDAO.deleteSeed(seed);
            }
        }
    }
}
