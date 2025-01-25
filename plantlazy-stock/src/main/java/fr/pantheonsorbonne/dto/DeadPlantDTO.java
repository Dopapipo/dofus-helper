
package fr.pantheonsorbonne.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.pantheonsorbonne.entity.enums.PlantType;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore les champs non définis dans DeadPlantDTO
public record DeadPlantDTO(@JsonProperty("type") PlantType plantType, int quantity) {

}
