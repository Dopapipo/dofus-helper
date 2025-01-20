package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.ResourceType;

public record MessageLogDTO(
        ResourceType resourceType,
        Double quantityBefore,
        Double quantityChange,
        Double quantityAfter,
        OperationTag operationTag
) {

}