package fr.pantheonsorbonne.camel.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.SeedToFarmDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SeedProducer {

    @Inject
    @ConfigProperty(name = "plant.seed.endpoint")
    String seedEndpoint;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    ProducerTemplate producerTemplate;


    public void sendSeedMessage(SeedToFarmDTO seedToFarmDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(seedToFarmDTO);

            producerTemplate.sendBody(seedEndpoint, jsonMessage);
            System.out.println("Message sent for seed: " + jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing SeedToFarmDTO to JSON", e);
        }
    }
}