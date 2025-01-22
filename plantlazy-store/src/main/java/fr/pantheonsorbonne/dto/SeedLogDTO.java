package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;

public record SeedLogDTO(PlantType seedType, long quantity, LogType type) {
}
