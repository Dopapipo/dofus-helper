package fr.pantheonsorbonne.exception;

import fr.pantheonsorbonne.entity.enums.PlantType;

public class PlantNotFoundException extends BaseException {

    public PlantNotFoundException(PlantType plantType) {
        super("Type de plante non trouvé: " + plantType);
    }
}
