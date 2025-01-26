package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.entity.enums.ResourceType;

public record ResourceUpdateDTO(ResourceType type, Double quantity, OperationTag operationTag) {

}
