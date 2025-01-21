package fr.pantheonsorbonne.dto;


import fr.pantheonsorbonne.entity.seed.SeedQuality;

public record SeedSaleDTO(PlantType seedType, int quantity, SeedQuality quality) {
}

