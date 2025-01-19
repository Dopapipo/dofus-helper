package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.dto.ResourceDTO;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.EnumSet;
import java.util.List;

@ApplicationScoped
public class StockService {

    @Inject
    ResourceValidationService validationService;

    @Inject
    ResourceNotificationService notificationService;

    @Inject
    ResourceDAO resourceDAO;

    public static final double DAILY_LIMIT = 1000.0;

    private static final EnumSet<ResourceType> REFILLABLE_TYPES = EnumSet.of(ResourceType.WATER, ResourceType.ENERGY);

    public ResourceDTO updateResource(ResourceType type, Double quantity, String operationTag) {
        validationService.validateQuantity(quantity);
        Resource resource = validationService.getResourceOrThrow(type, resourceDAO);
        Double quantityBefore = resource.getQuantity();

        validationService.validateResourceUpdate(resource, type, quantity);
        Resource updatedResource = updateResourceQuantity(resource, quantity);

        notificationService.notifyResourceUpdate(type, quantityBefore, quantity, updatedResource.getQuantity(), operationTag);

        return ResourceDTO.fromEntity(updatedResource, operationTag);
    }

    public void refillDailyResource() {
        List<Resource> allResources = resourceDAO.findAll();
        allResources.stream().filter(resource -> REFILLABLE_TYPES.contains(resource.getType())).forEach(resource -> {
            resource.setQuantity(DAILY_LIMIT);
            resourceDAO.save(resource);
        });
    }

    private Resource updateResourceQuantity(Resource resource, Double quantity) {
        resource.setQuantity(resource.getQuantity() + quantity);
        return resourceDAO.save(resource);
    }
}