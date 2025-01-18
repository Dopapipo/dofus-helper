package fr.pantheonsorbonne.exception;

public class PlantNotFoundException extends BaseException {

    public PlantNotFoundException(String plantType) {
        super("Type de plante non trouvé: " + plantType);
    }
}
