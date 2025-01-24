package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.plant.PlantType;


import java.util.UUID;

public record LogMessagePlantCreatedOrUpdated(LogType type, UUID id, PlantType plantType,
                                              int water, int sun, int soil) {
}
