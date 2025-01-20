package fr.pantheonsorbonne.camel.client;

import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.PurchaseRequestDTO;
import fr.pantheonsorbonne.dto.ResourceResponseDTO;
import fr.pantheonsorbonne.dto.ResourceType;
import fr.pantheonsorbonne.service.StockService;
import fr.pantheonsorbonne.service.StoreService;
import jakarta.inject.Inject;
import java.util.List;

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

    public void buySeed(PurchaseRequestDTO purchaseRequest) {
        storeService.buySeed(purchaseRequest.seedType(), purchaseRequest.quantity());
    }




}
