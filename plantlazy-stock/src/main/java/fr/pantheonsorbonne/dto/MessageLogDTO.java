package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.entity.enums.ResourceType;

public record MessageLogDTO(
        ResourceType resourceType,
        Double quantityBefore,
        Double quantityChange,
        Double quantityAfter,
        OperationTag operationTag,
        String type

) {

}