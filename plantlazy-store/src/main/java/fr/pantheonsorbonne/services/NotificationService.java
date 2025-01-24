package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.producer.PlantProducer;
import fr.pantheonsorbonne.camel.producer.SeedProducer;
import fr.pantheonsorbonne.dto.LogType;
import fr.pantheonsorbonne.dto.PlantInShopLogDTO;
import fr.pantheonsorbonne.dto.PlantSoldLogDTO;
import fr.pantheonsorbonne.dto.SeedInShopLogDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class NotificationService {

    @Inject
    SeedProducer seedProducer;

    @Inject
    PlantProducer plantProducer;


    public void notifySeedUpdate(List<SeedEntity> seeds) {
        SeedInShopLogDTO seedInShopLogDTO = new SeedInShopLogDTO(seeds, LogType.STORE_SELLABLE_SEEDS);
        seedProducer.sendSeedInShopLog(seedInShopLogDTO);
    }

    public void notifyPlantInShop(UUID id ,PlantType plantType, double price) {
        PlantInShopLogDTO plantLog = new PlantInShopLogDTO(id, plantType, price, LogType.STORE_SELLABLE_PLANT);
        plantProducer.sendPlantInShopLog(plantLog);
    }

    public void notifyPlantSold(UUID id) {
        PlantSoldLogDTO plantLog = new PlantSoldLogDTO(id, LogType.STORE_SOLD_PLANT);
        plantProducer.sendPlantSoldLog(plantLog);

    }
}
