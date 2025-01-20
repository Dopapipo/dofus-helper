package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;

public class ResourceUpdateDTO {
    private ResourceType type;
    private Double quantity;
    private OperationTag operationTag;

    public ResourceUpdateDTO(ResourceType type, Double quantity, OperationTag operationTag) {
        this.type = type;
        this.quantity = quantity;
        this.operationTag = operationTag;
    }

    public static ResourceUpdateDTO fromEntity(Resource resource, OperationTag operationTag) {
        return new ResourceUpdateDTO(
                resource.getType(),
                resource.getQuantity(),
                operationTag
        );
    }

    public ResourceType getType() {
        return type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public OperationTag getOperationTag() {
        return this.operationTag;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setOperationTag(OperationTag operationTag) {
        this.operationTag = operationTag;
    }
}