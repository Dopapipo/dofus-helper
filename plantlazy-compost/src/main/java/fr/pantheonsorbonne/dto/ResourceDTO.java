package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;

public class ResourceDTO {
    private final Long id;
    private ResourceType type;
    private Double quantity;


    public ResourceDTO(Long id, ResourceType type, Double quantity) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
    }

    public static ResourceDTO fromEntity(Resource resource) {
        return new ResourceDTO(
                resource.getId(),
                resource.getType(),
                resource.getQuantity()
        );
    }

    public Long getId() {
        return id;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}