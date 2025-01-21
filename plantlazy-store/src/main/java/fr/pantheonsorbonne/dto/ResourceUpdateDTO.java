package fr.pantheonsorbonne.dto;


import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.ResourceType;

public record ResourceUpdateDTO(ResourceType type, Double quantity, PlantType.OperationTag operationTag) {

}
