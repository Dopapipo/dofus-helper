package fr.pantheonsorbonne.exception;

public class InsufficientStockException extends BaseException {

    public InsufficientStockException(String plantType, int availableStock, int requestedQuantity) {
        super("Insufficient stock for plant type: " + plantType +
                ". Available: " + availableStock + ", Requested: " + requestedQuantity);
    }
}
