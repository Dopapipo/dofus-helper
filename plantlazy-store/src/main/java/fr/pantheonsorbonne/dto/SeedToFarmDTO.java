package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;

import java.io.Serial;
import java.io.Serializable;

public record  SeedToFarmDTO(PlantType seedType, SeedQuality seedQuality) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

