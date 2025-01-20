package fr.pantheonsorbonne.camel.client;

import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.ResourceResponseDTO;
import fr.pantheonsorbonne.service.StockService;
import fr.pantheonsorbonne.service.StoreService;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class PlantLazyClient {

    @Inject
    StoreService storeService;

    @Inject
    StockService stockService;

    public List<DailySeedOfferDTO> requestDailySeedOffer() {
        return storeService.getAvailableSeeds();
    }

    public ResourceResponseDTO requestMoneyAvailable() {
        return stockService.updateResource()
    }


}
