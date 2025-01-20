package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;

public class ResourceDTO {
    private ResourceType type;
    private Double quantity;
    private String operationTag;

    public ResourceDTO(ResourceType type, Double quantity, String operationTag) {
        this.type = type;
        this.quantity = quantity;
        this.operationTag = operationTag;
    }

    public static ResourceDTO fromEntity(Resource resource, String operationTag) {
        return new ResourceDTO(
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

    public String getOperationTag() {
        return operationTag;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setOperationTag(String operationTag) {
        this.operationTag = operationTag;
    }

    @Override
    public String toString() {
        return "ResourceDTO{" +
                ", type=" + type +
                ", quantity=" + quantity +
                ", operationTag='" + operationTag + '\'' +
                '}';
    }
}