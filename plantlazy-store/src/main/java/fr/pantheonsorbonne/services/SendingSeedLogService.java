package fr.pantheonsorbonne.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.PlantSaleLogDTO;
import fr.pantheonsorbonne.dto.SeedLogDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class SendingSeedLogService {

    @Inject
    ProducerTemplate producerTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendSeedLog(String endpointUri, SeedLogDTO seedLogDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(seedLogDTO);

            Exchange exchange = producerTemplate.getCamelContext().getEndpoint(endpointUri).createExchange();
            Message message = exchange.getIn();
            message.setBody(jsonMessage);

            producerTemplate.send(endpointUri, exchange);

            System.out.println("Message sent" + seedLogDTO);

        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize SeedLogDTO to JSON", e);
        }
    }


    public void sendPlantLog(String endpointUri, PlantSaleLogDTO plantDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(plantDTO);
            producerTemplate.sendBody(endpointUri, jsonMessage);

            Exchange exchange = producerTemplate.getCamelContext().getEndpoint(endpointUri).createExchange();
            Message message = exchange.getIn();

            message.setBody(jsonMessage);
            producerTemplate.send(endpointUri, exchange);

        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize MessageLogDTO to JSON", e);
        }
    }


}
