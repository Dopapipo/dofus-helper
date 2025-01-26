package fr.pantheonsorbonne.camel.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.SeedInShopLogDTO;
import fr.pantheonsorbonne.dto.SeedToFarmDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SeedProducer {

    @Inject
    @ConfigProperty(name = "plant.seed.endpoint")
    String seedEndpoint;

    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;

    @Inject
    ObjectMapper objectMapper;

    @Inject
    ProducerTemplate producerTemplate;


    public void sendSeedMessageToFarm(SeedToFarmDTO seedToFarmDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(seedToFarmDTO);

            producerTemplate.sendBody(seedEndpoint, jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing SeedToFarmDTO to JSON", e);
        }
    }

    public void sendSeedInShopLog(SeedInShopLogDTO seedInShopLogDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(seedInShopLogDTO);

            Exchange exchange = producerTemplate.getCamelContext().getEndpoint(logEndpoint).createExchange();
            Message message = exchange.getIn();
            message.setBody(jsonMessage);

            producerTemplate.send(logEndpoint, exchange);


        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize SeedLogDTO to JSON", e);
        }
    }
}
