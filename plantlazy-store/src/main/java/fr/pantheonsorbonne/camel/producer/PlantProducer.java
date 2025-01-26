package fr.pantheonsorbonne.camel.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.PlantInShopLogDTO;
import fr.pantheonsorbonne.dto.PlantSoldLogDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantProducer {

    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    ObjectMapper objectMapper;

    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;

    public void sendPlantInShopLog(PlantInShopLogDTO plantDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(plantDTO);
            producerTemplate.sendBody(logEndpoint, jsonMessage);

            Exchange exchange = producerTemplate.getCamelContext().getEndpoint(logEndpoint).createExchange();
            Message message = exchange.getIn();

            message.setBody(jsonMessage);
            producerTemplate.send(logEndpoint, exchange);
            System.out.println(jsonMessage);
            System.out.println("eeeeee");

        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize MessageLogDTO to JSON", e);
        }
    }

    public void sendPlantSoldLog(PlantSoldLogDTO plantDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(plantDTO);
            producerTemplate.sendBody(logEndpoint, jsonMessage);

            Exchange exchange = producerTemplate.getCamelContext().getEndpoint(logEndpoint).createExchange();
            Message message = exchange.getIn();

            message.setBody(jsonMessage);
            producerTemplate.send(logEndpoint, exchange);

        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize MessageLogDTO to JSON", e);
        }


    }
}
