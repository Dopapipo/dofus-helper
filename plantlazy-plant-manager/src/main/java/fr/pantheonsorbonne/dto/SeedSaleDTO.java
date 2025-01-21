package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.SeedQuality;

public record SeedSaleDTO(PlantType seedType, int quantity, SeedQuality quality) {
}

