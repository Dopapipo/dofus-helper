package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;

public record SeedPriceDTO(PlantType seedType, double price, SeedQuality seedQuality) {
}

