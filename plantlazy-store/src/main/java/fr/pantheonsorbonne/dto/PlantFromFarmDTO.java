package fr.pantheonsorbonne.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.pantheonsorbonne.entity.enums.PlantType;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PlantFromFarmDTO(
        @JsonProperty("type") PlantType plantType, // Mapper "type" vers plantType
        double price
) {
}

