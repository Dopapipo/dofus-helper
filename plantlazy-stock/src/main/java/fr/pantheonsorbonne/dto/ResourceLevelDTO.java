package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.Resource;

public record ResourceLevelDTO (Double quantity){

    public static ResourceLevelDTO fromEntity(Resource resource) {
        return new ResourceLevelDTO(resource.getQuantity());
    }
}
