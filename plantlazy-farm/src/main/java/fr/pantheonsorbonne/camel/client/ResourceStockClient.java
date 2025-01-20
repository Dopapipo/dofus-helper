package fr.pantheonsorbonne.camel.client;
import fr.pantheonsorbonne.dto.ResourceRequest;
import fr.pantheonsorbonne.entity.plant.stat.StatType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ResourceStockClient {

    @ConfigProperty(name="stock.api.url")
    String STOCK_API_URL;
    private final Client client = ClientBuilder.newClient();

    public boolean requestResource(StatType resourceType, int quantity) {
        ResourceRequest request = new ResourceRequest(resourceType, quantity, "STOCK_QUERIED");
        Response response = client.target(STOCK_API_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(request));

        return response.getStatus() == 200;
    }
}
