
package fr.pantheonsorbonne.plantlazy.manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MessageProducerService {

    @Inject
    private ProducerTemplate producerTemplate;

    @Inject
    private ObjectMapper objectMapper;

    public void sendMessageToRoute(String route, Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            producerTemplate.sendBody(route, jsonMessage);
            System.out.println("Message sent to route " + route + ": " + jsonMessage);
        } catch (Exception e) {
            System.err.println("Error sending message to route " + route + ": " + e.getMessage());
        }
    }
}
