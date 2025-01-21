package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.DeadPlantDTO;
import fr.pantheonsorbonne.dto.ResourceResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class CompostService {

    @Inject
    @RestClient
    StockClient stockClient;

    public void processDeadPlant() {
        int fertilizerQuantity = ThreadLocalRandom.current().nextInt(50, 76);

        ResourceResponseDTO fertilizerUpdate = new ResourceResponseDTO(fertilizerQuantity);
        fertilizerUpdate.setResourceType("FERTILIZER");
        fertilizerUpdate.setQuantity(fertilizerQuantity);
        fertilizerUpdate.setOperationTag("STOCK_RECEIVED");

        stockClient.updateStock(fertilizerUpdate);
    }
}
