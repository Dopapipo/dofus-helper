package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;

public record PurchaseRequestDTO(PlantType seedType, SeedQuality seedQuality, double totalAmount) {
}
