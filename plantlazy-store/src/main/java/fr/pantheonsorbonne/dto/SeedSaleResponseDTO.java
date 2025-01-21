package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;

public record SeedSaleResponseDTO(PlantType seedType, int quantity, SeedQuality quality) {
}
