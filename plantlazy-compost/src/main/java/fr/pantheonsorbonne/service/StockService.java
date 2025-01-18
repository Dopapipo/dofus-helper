package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.dto.ResourceDTO;
import fr.pantheonsorbonne.dto.ResourceMessage;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;
import fr.pantheonsorbonne.exception.DailyLimitExceededException;
import fr.pantheonsorbonne.exception.InsufficientResourceException;
import fr.pantheonsorbonne.exception.InvalidQuantityException;
import fr.pantheonsorbonne.exception.ResourceNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StockService {

    @Inject
    ResourceDAO resourceDAO;

    private static final double DAILY_LIMIT = 1000.0;

    @Inject
    MessageProducerService producerTemplate;

    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;


    public ResourceDTO updateResource(ResourceType type, Double quantity) {
        validateQuantity(quantity);
        Resource resource = getResourceOrThrow(type);
        Double quantityBefore = resource.getQuantity();

        validateResourceUpdate(resource, type, quantity);
        Resource updatedResource = updateResourceQuantity(resource, quantity);

        notifyResourceUpdate(type, quantityBefore, quantity, updatedResource.getQuantity());

        return ResourceDTO.fromEntity(updatedResource);
    }

    private void validateQuantity(Double quantity) {
        if (quantity == null || quantity == 0) {
            throw new InvalidQuantityException("Quantity cannot be null or zero");
        }
    }

    private Resource getResourceOrThrow(ResourceType type) {
        return resourceDAO.findByType(type)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found: " + type));
    }

    private void validateResourceUpdate(Resource resource, ResourceType type, Double quantity) {
        if (!isWithinDailyLimit(resource, quantity)) {
            throw new DailyLimitExceededException("Daily limit exceeded for " + type);
        }
        validateSufficientResource(resource, type, quantity);
    }

    private void validateSufficientResource(Resource resource, ResourceType type, Double quantity) {
        if (resource.getQuantity() + quantity < 0) {
            throw new InsufficientResourceException("Insufficient " + type);
        }
    }

    private Resource updateResourceQuantity(Resource resource, Double quantity) {
        resource.setQuantity(resource.getQuantity() + quantity);
        return resourceDAO.save(resource);
    }

    private void notifyResourceUpdate(ResourceType type, Double quantityBefore, Double quantityChange, Double newQuantity) {
        ResourceMessage message = new ResourceMessage(type, quantityBefore, quantityChange, newQuantity);
        producerTemplate.sendMessageToRoute("direct:resourceUpdateRoute", message);
    }

    public boolean isWithinDailyLimit(Resource resource, double quantity) {
        double newUsage = resource.getQuantity() - quantity;
        return newUsage >= 0;
    }

    public List<ResourceDTO> RefillDailyResources() {
        List<ResourceType> typesToRefill = List.of(ResourceType.WATER, ResourceType.ENERGY);
        return typesToRefill.stream().map(type -> {
            Resource resource = resourceDAO.findByType(type).orElseThrow(() ->
                    new IllegalStateException("Resource not found for type: " + type));
            resource.setQuantity(DAILY_LIMIT);
            Resource updatedResource = resourceDAO.save(resource);
            return ResourceDTO.fromEntity(updatedResource);
        }).collect(Collectors.toList());
    }
}