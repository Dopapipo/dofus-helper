package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;

// offre quotidienne de graines disponibles

public record DailySeedOfferDTO(PlantType seedType, SeedQuality seedQuality, double price) {
}

