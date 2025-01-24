package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.dto.DeadPlantDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
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
        double fertilizerQuantity = calculateFertilizer(deadPlant.getPlantType());

        double fertilizerBefore = resourceDAO.findByType(ResourceType.FERTILIZER).getQuantity();

        double fertilizerAfter = fertilizerBefore + fertilizerQuantity;

        stockService.updateResource(
                new ResourceUpdateDTO(ResourceType.FERTILIZER, fertilizerQuantity, OperationTag.STOCK_RECEIVED));
        resourceNotificationService.notifyResourceUpdate(ResourceType.FERTILIZER, fertilizerBefore, fertilizerQuantity, fertilizerAfter, OperationTag.STOCK_RECEIVED);
    }

    private double calculateFertilizer(PlantType plantType) {
        return switch (plantType) {
            case TREE -> ThreadLocalRandom.current().nextInt(80, 101);
            case CACTUS -> ThreadLocalRandom.current().nextInt(40, 71);
            case FLOWER -> ThreadLocalRandom.current().nextInt(15, 31);
        };
    }
}
