package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.dto.DeadPlantDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class CompostService {

    @Inject
    StockService stockService;

    @Inject
    ResourceNotificationService resourceNotificationService;

    @Inject
    ResourceDAO resourceDAO;

    public void processDeadPlant(DeadPlantDTO deadPlant) {
        double fertilizerQuantity = calculateFertilizer(deadPlant.plantType());

        Resource fertilizerResource = resourceDAO.findByType(ResourceType.FERTILIZER);

        double fertilizerBefore = (fertilizerResource != null) ? fertilizerResource.getQuantity() : 0.0;

        double fertilizerAfter = fertilizerBefore + fertilizerQuantity;

        stockService.updateResource(
                new ResourceUpdateDTO(ResourceType.FERTILIZER, fertilizerQuantity, OperationTag.STOCK_RECEIVED));
        resourceNotificationService.notifyResourceUpdate(ResourceType.FERTILIZER, fertilizerBefore, fertilizerQuantity, fertilizerAfter, OperationTag.STOCK_RECEIVED);
    }


    private double calculateFertilizer(PlantType plantType) {
        return switch (plantType) {
            case TREE -> ThreadLocalRandom.current().nextInt(180, 201);
            case CACTUS -> ThreadLocalRandom.current().nextInt(140, 171);
            case FLOWER -> ThreadLocalRandom.current().nextInt(115, 131);
        };
    }
}
