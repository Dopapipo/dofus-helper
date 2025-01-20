package fr.pantheonsorbonne.exception;

import fr.pantheonsorbonne.entity.enums.PlantType;

public class InsufficientStockException extends BaseException {

    public InsufficientStockException(PlantType plantType, int availableStock, int requestedQuantity) {
        super("Insufficient stock for plant type: " + plantType +
                ". Available: " + availableStock + ", Requested: " + requestedQuantity);
    }
}
