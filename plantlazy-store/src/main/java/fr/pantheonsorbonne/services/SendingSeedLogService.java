package fr.pantheonsorbonne.services;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void sendSeedLog(String endpointUri, SeedLogDTO seedPriceDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(seedPriceDTO);
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