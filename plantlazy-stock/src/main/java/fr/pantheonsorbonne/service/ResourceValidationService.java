package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;
import fr.pantheonsorbonne.exception.InsufficientResourceException;
import fr.pantheonsorbonne.exception.ResourceNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class ResourceValidationService {

    public void validateResourceUpdate(Resource resource, ResourceType type, Double quantity) {
        if (resource.getQuantity() + quantity < 0) {
            throw new InsufficientResourceException("Not enough resource of type: " + type);
        }
    }

    public Resource getResourceOrThrow(ResourceType type, ResourceDAO resourceDAO) {
        return resourceDAO.findByType(type)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found: " + type));
    }
}

