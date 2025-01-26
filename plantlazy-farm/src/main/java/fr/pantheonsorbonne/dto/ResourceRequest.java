package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.entity.enums.ResourceType;

public record ResourceRequest(ResourceType type, int quantity, OperationTag operationTag) {
}
