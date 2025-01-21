package fr.pantheonsorbonne.exception;

public class InsufficientStockException extends BaseException {

    public InsufficientStockException(int requestedQuantity) {
        super("Insufficient stock for plant" + requestedQuantity + "requested quantity");
    }
}
