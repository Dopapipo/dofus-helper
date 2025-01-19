package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.ResourceMessage;
import fr.pantheonsorbonne.entity.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
class ResourceNotificationService {

    @Inject
    MessageProducerService producerTemplate;

    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;

    public void notifyResourceUpdate(ResourceType type, Double quantityBefore, Double quantity, Double updatedQuantity, String operationTag) {
        ResourceMessage message = new ResourceMessage(type, quantityBefore, quantity, updatedQuantity, operationTag);
        producerTemplate.sendMessageToRoute(logEndpoint, message);
    }
}