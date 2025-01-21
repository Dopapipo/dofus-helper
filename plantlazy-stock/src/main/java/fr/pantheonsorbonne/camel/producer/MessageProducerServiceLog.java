package fr.pantheonsorbonne.camel.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.MessageLogDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;


@ApplicationScoped
public class MessageProducerServiceLog {

    @Inject
    ProducerTemplate producerTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessageToRoute(String endpointUri, MessageLogDTO messageLogDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(messageLogDTO);

            Exchange exchange = producerTemplate.getCamelContext().getEndpoint(endpointUri).createExchange();
            Message message = exchange.getIn();

            message.setBody(jsonMessage);

            producerTemplate.send(endpointUri, exchange);

        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize MessageLogDTO to JSON", e);
        }
    }
}
