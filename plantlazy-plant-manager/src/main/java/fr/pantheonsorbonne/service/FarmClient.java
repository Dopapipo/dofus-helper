
package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.DeadPlantDTO;

public class FarmClient {

    public void sendSeedsToFarm(String seedType, int quantity) {
        // Placeholder for API interaction to send seeds to Farm
    }

    public DeadPlantDTO getDeadPlants() {
        // Placeholder for API interaction to retrieve dead plants
        return new DeadPlantDTO("Corn", 5); // Example response
    }
}
