package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.ResourceType;

public record ResourceMessage(
        String resourceType,
        Double quantityBefore,
        Double quantityChange,
        Double quantityAfter
) {
    public ResourceMessage(ResourceType type, Double quantityBefore, Double quantityChange, Double quantityAfter) {
            this(type.name(), quantityBefore, quantityChange, quantityAfter);
    }
}