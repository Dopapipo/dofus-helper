package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.LogType;
import fr.pantheonsorbonne.entity.enums.PlantType;


import java.util.UUID;

public record LogMessagePlantCreatedOrUpdated(LogType type, UUID id, PlantType plantType,
                                              int water, int sun, int soil) {
}
