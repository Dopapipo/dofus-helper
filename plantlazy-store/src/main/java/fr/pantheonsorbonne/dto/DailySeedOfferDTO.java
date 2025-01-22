package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;

public record DailySeedOfferDTO(PlantType seedType, SeedQuality seedQuality, double price) {
}

