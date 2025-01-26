package fr.pantheonsorbonne.dto;

public record ResourceRequest(ResourceType type, int quantity, OperationTag operationTag) {
}
