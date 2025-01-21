package fr.pantheonsorbonne.camel.client;

import fr.pantheonsorbonne.dto.OperationTag;
import fr.pantheonsorbonne.dto.ResourceRequest;
import fr.pantheonsorbonne.exception.ResourceRequestDeniedException;
import fr.pantheonsorbonne.entity.plant.stat.StatType;
import fr.pantheonsorbonne.service.StockService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ResourceStockClient {

    @Inject
    @RestClient
    StockService stockService;

    public void requestResource(StatType resourceType, int quantity) throws ResourceRequestDeniedException {
        ResourceRequest request = new ResourceRequest(resourceType, quantity, OperationTag.STOCK_QUERIED);
        Response response = stockService.requestResource(request);
        if (response.getStatus() != 200) {
            throw new ResourceRequestDeniedException("Not enough resources available");
        }
    }
}
