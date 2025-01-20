
package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.DeadPlantDTO;

public class CompostService {

    public int transformToCompost(DeadPlantDTO deadPlant) {
        // Example logic: 1 dead plant = 2 units of compost
        return deadPlant.getQuantity() * 2;
    }
}
