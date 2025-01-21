package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.MessageLogDTO;
import fr.pantheonsorbonne.dto.OperationTag;
import fr.pantheonsorbonne.entity.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
class ResourceNotificationService {

    @Inject
    MessageProducerServiceLog messageProducerServiceLog;

    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;

    public void notifyResourceUpdate(ResourceType ressourceType, Double quantityBefore, Double quantity, Double updatedQuantity, OperationTag operationTag) {
        MessageLogDTO ressourceMessageDTO = new MessageLogDTO(ressourceType, quantityBefore, quantity, updatedQuantity, operationTag, "RESOURCE_UPDATE");
        messageProducerServiceLog.sendMessageToRoute(logEndpoint, ressourceMessageDTO);
    }
}