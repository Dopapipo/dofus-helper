package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.camel.client.StockClient;
import fr.pantheonsorbonne.dto.ResourceMoneyDTO;
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
        Response resourceMoney = stockClient.getResourceByType(ResourceType.MONEY);

        if (resourceMoney.getStatus() == Response.Status.OK.getStatusCode()) {
            ResourceMoneyDTO resourceMoneyDTO = resourceMoney.readEntity(ResourceMoneyDTO.class);
            return resourceMoneyDTO.quantity();
        } else {
            throw new RuntimeException("Failed to fetch MONEY resource. HTTP status: " + resourceMoney.getStatus());
        }
    }


}
