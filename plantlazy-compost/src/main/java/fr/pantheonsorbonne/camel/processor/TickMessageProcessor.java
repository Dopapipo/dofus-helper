package fr.pantheonsorbonne.camel.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.TickMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class TickMessageProcessor implements Processor {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setBody(
                objectMapper.readValue(exchange.getIn().getBody(String.class), TickMessage.class)
        );
    }
}
