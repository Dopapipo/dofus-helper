package fr.pantheonsorbonne.exception;

import fr.pantheonsorbonne.entity.ResourceType;

public class InsufficientResourceException extends RuntimeException {
    public InsufficientResourceException(ResourceType type) {
        super("Insufficient " + type.name().toLowerCase() + " available");
    }
}