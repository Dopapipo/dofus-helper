package fr.pantheonsorbonne.camel.client;

import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.dto.ResourceRequest;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.exception.ResourceRequestDeniedException;
import fr.pantheonsorbonne.entity.plant.stat.StatType;
import fr.pantheonsorbonne.mapper.TypeMapper;
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

    public void requestResource(StatType statType, int quantity) throws ResourceRequestDeniedException {
        ResourceType resourceType = TypeMapper.toResourceType(statType);
        ResourceRequest request = new ResourceRequest(resourceType, quantity, OperationTag.STOCK_QUERIED);

        try {
            Response response = stockService.updateResource(request);

            if (response.getStatus() != 202) {
                throw new ResourceRequestDeniedException("Not enough resources available");
            }

        } catch (jakarta.ws.rs.WebApplicationException e) {
            int statusCode = e.getResponse().getStatus();
            if (statusCode == 400) {
                throw new ResourceRequestDeniedException("Resource unavailable for type: " + resourceType);
            } else if (statusCode == 500) {
                throw new ResourceRequestDeniedException("Stock service is currently unavailable");
            } else {
                throw new ResourceRequestDeniedException("Unexpected error: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new ResourceRequestDeniedException("An unexpected error occurred: " + e.getMessage());
        }
    }
}
