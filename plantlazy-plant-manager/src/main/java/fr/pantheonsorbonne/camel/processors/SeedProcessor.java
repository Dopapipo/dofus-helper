
package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.TickMessageDTO;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.service.StoreClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SeedProcessor implements Processor {

    @Inject
    StoreClient storeClient;

    @Override
    public void process(Exchange exchange) throws Exception {
        // ecrire la logique pour transformer ce que je recois en le DTO attendu par le ms farm
        TickMessageDTO tickMessage = exchange.getIn().getBody(TickMessageDTO.class);
        if (tickMessage == null) {
            throw new IllegalArgumentException("Received null or invalid TickMessage");
        }

        if (tickMessage.getTickType() == TickType.HOURLY) {
            // Fetch seed prices from the store service
            List<Map<String, Object>> seeds = storeClient.getAvailableSeeds();
            System.out.println("Fetched available seeds: " + seeds);
        }
    }
}
