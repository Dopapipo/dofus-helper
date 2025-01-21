package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.DeadPlantDTO;
import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class CompostService {

    @Inject
    StockService StockService;

    double totalFertilizer = 0;

    public void processDeadPlant(DeadPlantDTO deadPlant) {
        for (int i = 0; i < deadPlant.getQuantity(); i++) {
            double fertilizerQuantity = 0;
            if (deadPlant.getPlantType() == PlantType.TREE) {
                fertilizerQuantity = ThreadLocalRandom.current().nextInt(80, 101);
            } else if (deadPlant.getPlantType() == PlantType.CACTUS) {
                fertilizerQuantity = ThreadLocalRandom.current().nextInt(40, 71);
            } else if (deadPlant.getPlantType() == PlantType.FLOWER) {
                fertilizerQuantity = ThreadLocalRandom.current().nextInt(15, 31);
            }

            totalFertilizer += fertilizerQuantity;
        }

        StockService.updateResource(new ResourceUpdateDTO(ResourceType.FERTILIZER, totalFertilizer, OperationTag.STOCK_RECEIVED));

    }
}
