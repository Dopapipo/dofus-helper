package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

public record PlantSaleLogDTO(PlantType plantType, double price, LogType type) {
}
