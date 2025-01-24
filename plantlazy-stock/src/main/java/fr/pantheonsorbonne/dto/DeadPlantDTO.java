
package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

public record DeadPlantDTO(PlantType plantType, int quantity) {

    public PlantType getPlantType() {
        return plantType;
    }
}
