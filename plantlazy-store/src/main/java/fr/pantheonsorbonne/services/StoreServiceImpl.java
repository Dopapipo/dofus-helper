package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class StoreServiceImpl implements StoreService {

    @Inject
    @RestClient
    StockClient stockClient;

    @Override
    public double getAvailableMoney() {
        Response response = stockClient.getResource(String.valueOf(ResourceType.MONEY));
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Unable to fetch available money from stock service.");
        }
        return response.readEntity(Integer.class);
    }
}
