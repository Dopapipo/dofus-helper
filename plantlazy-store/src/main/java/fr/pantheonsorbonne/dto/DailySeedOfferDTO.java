package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

// offre quotidienne de graines disponibles
public record DailySeedOfferDTO(PlantType seedType, int quantity, double price) {
}

