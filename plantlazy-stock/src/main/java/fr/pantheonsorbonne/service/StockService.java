package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.entity.enums.OperationTag;
import fr.pantheonsorbonne.dto.ResourceLevelDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import fr.pantheonsorbonne.exception.ResourceNotFoundException;
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

    private ResourceUpdateDTO handleUpdate(ResourceType ressourceType, Double quantity, OperationTag operationTag) {
        Resource resource = validationService.getResourceOrThrow(ressourceType, resourceDAO);
        Double quantityBefore = resource.getQuantity();

        validationService.validateResourceUpdate(resource, ressourceType, quantity);
        Resource updatedResource = updateResourceQuantity(resource, quantity);

        notificationService.notifyResourceUpdate(ressourceType, quantityBefore, quantity, updatedResource.getQuantity(), operationTag);

        resourceDAO.save(updatedResource);

        return ResourceUpdateDTO.fromEntity(updatedResource, operationTag);
    }

    public ResourceUpdateDTO updateResource(ResourceUpdateDTO resourceUpdateDTO) {
        if (resourceUpdateDTO.operationTag() == OperationTag.STOCK_RECEIVED) {
            return this.handleUpdate(resourceUpdateDTO.type(), resourceUpdateDTO.quantity(), resourceUpdateDTO.operationTag());
        } else {
            return this.handleUpdate(resourceUpdateDTO.type(), -resourceUpdateDTO.quantity(), resourceUpdateDTO.operationTag());
        }
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

    public ResourceLevelDTO getResourceValue(ResourceType resourceType) {
        Resource resource = resourceDAO.findByType(resourceType)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found: " + resourceType));
        return ResourceLevelDTO.fromEntity(resource);
    }
}