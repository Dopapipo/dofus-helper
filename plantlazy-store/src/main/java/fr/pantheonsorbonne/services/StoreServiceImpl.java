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
        // Appeler le client REST pour obtenir la ressource MONEY
        Response resourceMoney = stockClient.getResourceByType(ResourceType.MONEY);

        if (resourceMoney.getStatus() == Response.Status.OK.getStatusCode()) {
            // Lire la réponse comme un ResourceMoneyDTO
            ResourceMoneyDTO resourceMoneyDTO = resourceMoney.readEntity(ResourceMoneyDTO.class);
            return resourceMoneyDTO.quantity(); // Retourne la quantité depuis le DTO
        } else {
            // Gérer les erreurs
            throw new RuntimeException("Failed to fetch MONEY resource. HTTP status: " + resourceMoney.getStatus());
        }
    }


}
