package fr.pantheonsorbonne.exception;

public class SaleNotCompletedException extends BaseException {

    public SaleNotCompletedException(String plantType) {
        super("The sale of plant type '" + plantType + "' did not occur due to low probability.");
    }
}
