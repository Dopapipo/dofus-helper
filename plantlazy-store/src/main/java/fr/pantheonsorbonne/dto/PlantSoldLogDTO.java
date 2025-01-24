package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.UUID;

public record PlantSoldLogDTO(UUID id, LogType type) {
}