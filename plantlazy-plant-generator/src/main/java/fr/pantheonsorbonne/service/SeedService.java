
package fr.pantheonsorbonne.plantlazy.manager.service;

import fr.pantheonsorbonne.plantlazy.manager.dto.SeedDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SeedService {

    public void processSeeds(SeedDTO seed) {
        System.out.println("Processing seeds of type: " + seed.getType() + " with quantity: " + seed.getQuantity());
        // Business logic to update inventory with received seeds
        // Example: Add seeds to a stock database
    }
}
