package fr.pantheonsorbonne.camel.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.PlantDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantTransportProducer {
    @ConfigProperty(name = "dead.plant.transport.endpoint")
    String transportEndpoint;

    @ConfigProperty(name = "store.plant.transport.endpoint")
    String storePlantEndpoint;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    ProducerTemplate producerTemplate;


    public void sendPlantToStore(PlantDTO plantDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(plantDTO);

            producerTemplate.sendBody(storePlantEndpoint, jsonMessage);
            System.out.println("Message sent for plant: " + jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing PlantDTO to JSON", e);
        }
    }

    public void sendDeadPlant(PlantDTO plantDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(plantDTO);

            producerTemplate.sendBody(transportEndpoint, jsonMessage);
            System.out.println("Message sent for dead plant: " + jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing PlantDTO to JSON", e);
        }
    }

}


