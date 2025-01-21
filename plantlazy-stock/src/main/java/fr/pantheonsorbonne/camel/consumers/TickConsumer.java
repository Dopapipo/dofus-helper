package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.TickMessageProcessor;
import fr.pantheonsorbonne.dto.TickMessageDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TickConsumer extends RouteBuilder {

    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    @Inject
    TickMessageProcessor tickMessageProcessor;

    @Override
    public void configure() {
        from(tickEndpoint)
                .unmarshal().json(TickMessageDTO.class)
                .filter(exchange -> {
                    TickMessageDTO tickMessageDTO = exchange.getIn().getBody(TickMessageDTO.class);
                    return "DAILY".equalsIgnoreCase(tickMessageDTO.getTickType());
                })
                .process(tickMessageProcessor);
    }
}
