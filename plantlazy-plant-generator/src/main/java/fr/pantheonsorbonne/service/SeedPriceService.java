
package fr.pantheonsorbonne.plantlazy.manager.service;

import fr.pantheonsorbonne.plantlazy.manager.dto.SeedPriceDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SeedPriceService {

    public void processSeedPrice(SeedPriceDTO seedPrice) {
        System.out.println("Processing seed price: " + seedPrice.getSeedType() + " at price " + seedPrice.getPrice());
        // Business logic to update seed prices in the system
        // Example: Save the price to a database or update in-memory cache
    }
}
