package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.Resource;

public class ResourceLevelDTO {

    private Double quantity;

    public ResourceLevelDTO(Double quantity) {
        this.quantity = quantity;
    }

    public static ResourceLevelDTO fromEntity(Resource resource) {
        return new ResourceLevelDTO(resource.getQuantity());
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
