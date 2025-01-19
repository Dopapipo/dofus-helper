package fr.pantheonsorbonne.exception;

import fr.pantheonsorbonne.entity.enums.PlantType;

public class SaleNotCompletedException extends BaseException {

    public SaleNotCompletedException(PlantType plantType) {
        super("The sale of plant type '" + plantType + "' did not occur due to low probability.");
    }
}
