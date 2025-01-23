package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.camel.producer.SeedProducer;
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
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class SeedServiceImpl implements SeedService {

    @Inject
    SeedDAO seedDAO;

    @Inject
    SeedNotificationService seedNotificationService;

    @Inject
    StoreService storeService;

    @Inject
    SeedProducer SeedProducer;

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
        // Supprimer toutes les graines existantes
        seedDAO.deleteAllSeeds();

        // Définir un prix fixe pour chaque type de plante pour ce tick
        Map<PlantType, Double> fixedPrices = new HashMap<>();
        for (PlantType plantType : PlantType.values()) {
            double price = 20 + (30 * random.nextDouble());
            fixedPrices.put(plantType, Math.round(price * 100.0) / 100.0); // Arrondi à 2 décimales
        }

        // Générer les graines
        int numberOfSeedsToGenerate = 10 + random.nextInt(21);
        List<SeedEntity> generatedSeeds = new ArrayList<>();
        for (int i = 0; i < numberOfSeedsToGenerate; i++) {
            SeedQuality dailyQuality = generateRandomSeedQuality();
            PlantType dailyPlantType = generateRandomPlantType();

            SeedEntity seed = new SeedEntity();
            seed.setPrice(fixedPrices.get(dailyPlantType)); // Utilise le prix fixe
            seed.setQuality(dailyQuality);
            seed.setType(dailyPlantType);

            seedDAO.saveSeed(seed);
            generatedSeeds.add(seed);
        }

        // Envoyer les logs une fois toutes les graines générées
        sendSeedLog();

        System.out.println("Daily seeds generated");
    }

    private void sendSeedLog() {
        List<SeedEntity> allSeeds = seedDAO.getAllSeeds();

        seedNotificationService.notifySeedUpdate(allSeeds);

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

                SeedToFarmDTO seedDTO = new SeedToFarmDTO(seed.getType(), seed.getQuality());
                SeedProducer.sendSeedMessage(seedDTO);

                System.out.println("Seed of type " + seed.getType() + " sold for " + seed.getPrice() + "€");
                seedNotificationService.notifyPlantSale(seed.getType(), seed.getPrice());
                seedDAO.deleteSeed(seed);

            } else {
                StockClient.updateResource(new ResourceUpdateDTO(ResourceType.MONEY, storeService.getAvailableMoney() - (storeService.getAvailableMoney() - availableMoney), PlantType.OperationTag.STOCK_QUERIED));
                break;
            }
        }
    }

}
