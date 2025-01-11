package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ResourceDAO;
import fr.pantheonsorbonne.dto.ResourceMessage;
import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;
import fr.pantheonsorbonne.exception.DailyLimitExceededException;
import fr.pantheonsorbonne.exception.InsufficientResourceException;
import fr.pantheonsorbonne.exception.InvalidQuantityException;
import fr.pantheonsorbonne.exception.ResourceNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@ApplicationScoped
public class StockService {
    @Inject
    ResourceDAO resourceDAO;

    @Inject
    MessageProducerService producerTemplate;

    public void updateResource(ResourceType type, Double quantity) {
        if (quantity == null || quantity == 0) {
            throw new InvalidQuantityException("Quantity cannot be null or zero");
        }

        Resource resource = resourceDAO.findByType(type).orElseThrow(() -> new ResourceNotFoundException("Resource not found: " + type));

        Double quantityBefore = resource.getQuantity();

        if ((type == ResourceType.WATER || type == ResourceType.ENERGY) && !isWithinDailyLimit(resource, quantity)) {
            throw new DailyLimitExceededException("Daily limit exceeded for " + type);
        }

        if (resource.getQuantity() + quantity < 0) {
            throw new InsufficientResourceException("Insufficient " + type);
        }

        resource.setQuantity(resource.getQuantity() + quantity);
        resourceDAO.save(resource);

        ResourceMessage message = new ResourceMessage(type, quantityBefore, quantity, resource.getQuantity());

        producerTemplate.sendMessageToRoute("direct:resourceUpdateRoute", message);
    }

    public Double getResourceLevel(ResourceType type) {
        return resourceDAO.findByType(type).map(Resource::getQuantity).orElseThrow(() -> new ResourceNotFoundException("Resource not found: " + type));
    }

    private boolean isWithinDailyLimit(Resource resource, Double requestedQuantity) {
        if (resource.getLastRefreshDate().toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            resource.setLastRefreshDate(LocalDateTime.now());
            return true;
        }
        return Math.abs(requestedQuantity) <= resource.getDailyLimit();
    }

}