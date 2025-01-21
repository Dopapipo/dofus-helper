package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;

// prix des graines pour chaque type
public record SeedPriceDTO(PlantType seedType, double price, SeedQuality seedQuality) {
}

