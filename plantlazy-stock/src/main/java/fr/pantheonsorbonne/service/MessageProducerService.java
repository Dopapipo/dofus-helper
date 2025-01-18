package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.ResourceMessage;
import org.apache.camel.ProducerTemplate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MessageProducerService {

    @Inject
    ProducerTemplate producerTemplate;

    public void sendMessageToRoute(String endpointUri, Object message) {
        producerTemplate.sendBody(endpointUri, message);
    }

}