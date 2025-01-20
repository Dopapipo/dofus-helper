package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.ResourceType;

public record MessageLogDTO(
        String resourceType,
        Double quantityBefore,
        Double quantityChange,
        Double quantityAfter,
        OperationTag operationTag,
        String type
) {
    public MessageLogDTO(ResourceType type, Double quantityBefore, Double quantityChange, Double quantityAfter, OperationTag operationTag) {
        this(type.name(), quantityBefore, quantityChange, quantityAfter, operationTag, "UPDATE_RESOURCE");
    }

    @Override
    public String toString() {
        return "ResourceMessage{" +
                "resourceType='" + resourceType + '\'' +
                ", quantityBefore=" + quantityBefore +
                ", quantityChange=" + quantityChange +
                ", quantityAfter=" + quantityAfter +
                ", operationTag='" + operationTag + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}