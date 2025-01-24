package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

public record PlantFromFarmDTO(PlantType plantType) {
    public PlantType getPlantType() {
        return plantType;
    }



}

