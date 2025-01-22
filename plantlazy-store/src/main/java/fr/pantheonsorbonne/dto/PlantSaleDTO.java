package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

public record PlantSaleDTO(PlantType plantType) {
    public PlantType getPlantType() {
        return plantType;
    }



}

