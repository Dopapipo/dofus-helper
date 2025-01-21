package fr.pantheonsorbonne.camel.client;

import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.SeedSaleDTO;
import fr.pantheonsorbonne.dto.ResourceResponseDTO;
import fr.pantheonsorbonne.dto.ResourceType;
import fr.pantheonsorbonne.service.StockService;
import fr.pantheonsorbonne.service.StoreService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PlantLazyClient {

    @Inject
    StoreService storeService;

    @Inject
    StockService stockService;

    public List<DailySeedOfferDTO> requestDailySeedOffer() {
        return storeService.getAvailableSeeds();
    }

    public ResourceResponseDTO requestMoneyAvailable() {
        return stockService.getMoney(ResourceType.MONEY);
    }

    public SeedSaleDTO buySeed(SeedSaleDTO purchaseRequest) {
        return storeService.buySeed(purchaseRequest.seedType(), purchaseRequest.quantity());
    }




}
