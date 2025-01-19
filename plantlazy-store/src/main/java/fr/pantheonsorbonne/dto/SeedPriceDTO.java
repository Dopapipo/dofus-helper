package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

// prix des graines pour chaque type
public record SeedPriceDTO(PlantType seedType, double price) {
}

