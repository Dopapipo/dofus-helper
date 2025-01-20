package fr.pantheonsorbonne.camel.client;

import fr.pantheonsorbonne.dto.DailySeedOfferDTO;
import fr.pantheonsorbonne.dto.ResourceResponseDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class PlantLazyClient {
    @ConfigProperty(name = "store.plant.endpoint")
    String storePlantEndpoint;
    @ConfigProperty(name = "store.seed.endpoint")
    String storeSeedEndpoint;
    @ConfigProperty(name = "stock.endpoint")
    String stockEndpoint;

    private final Client client = ClientBuilder.newClient();

    public List<DailySeedOfferDTO> requestDailySeedOffer() {
        return client.target(storeSeedEndpoint)
                .request()
                .get(List.class);
    }

    public ResourceResponseDTO requestMoneyAvailable() {
        return client.target(stockEndpoint + "/MONEY")
                .request()
                .get(ResourceResponseDTO.class);
    }


}
