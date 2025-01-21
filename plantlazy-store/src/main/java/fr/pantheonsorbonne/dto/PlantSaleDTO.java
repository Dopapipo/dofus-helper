package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

// vente de plante, à envoyer au microservice Stock
public record PlantSaleDTO(PlantType plantType) {
    public PlantType getPlantType() {
        return plantType;
    }
}

