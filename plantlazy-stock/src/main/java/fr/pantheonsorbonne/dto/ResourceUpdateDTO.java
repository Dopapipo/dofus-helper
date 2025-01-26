package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.entity.enums.ResourceType;

public record ResourceUpdateDTO(ResourceType type, Double quantity, OperationTag operationTag) {

    public static ResourceUpdateDTO fromEntity(Resource updatedResource, OperationTag operationTag) {
        return new ResourceUpdateDTO(updatedResource.getType(), updatedResource.getQuantity(), operationTag);
    }
}
