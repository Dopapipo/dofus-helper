
package fr.pantheonsorbonne.plantlazy.manager.service;

import fr.pantheonsorbonne.plantlazy.manager.dto.DeadPlantDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeadPlantService {

    public void processDeadPlant(DeadPlantDTO deadPlant) {
        System.out.println("Processing dead plant with ID: " + deadPlant.getPlantId());
        // Business logic to handle dead plants
        // Example: Remove dead plant from active status, log the event
    }
}
