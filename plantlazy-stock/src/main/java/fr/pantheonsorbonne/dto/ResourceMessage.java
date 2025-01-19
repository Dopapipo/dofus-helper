package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.ResourceType;

public record ResourceMessage(
        String resourceType,
        Double quantityBefore,
        Double quantityChange,
        Double quantityAfter,
        String operationTag,
        String type
) {
    public ResourceMessage(ResourceType type, Double quantityBefore, Double quantityChange, Double quantityAfter, String operationTag) {
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
