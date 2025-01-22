package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.List;

public interface SeedService {
    List<SeedEntity> getAvailableSeeds();

    void updateDailySeedOffer();

    List<DailySeedOfferDTO> sellSeed();

    SeedEntity getCheapestSeed();

    List<PurchaseRequestDTO> getSeedPricingAndStock();

    double getPriceForTypeAndQuantity(PlantType seedType, int quantity);

    double getCalculateTotalPrice(int quantity);
}
