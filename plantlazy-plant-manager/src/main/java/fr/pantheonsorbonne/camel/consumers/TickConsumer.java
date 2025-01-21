
package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.SeedProcessor;
import fr.pantheonsorbonne.camel.processors.TickProcessor;
import fr.pantheonsorbonne.dto.TickMessageDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TickConsumer extends RouteBuilder {

    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    @Inject
    TickProcessor tickProcessor;
    @Inject
    SeedProcessor seedProcessor;

    @ConfigProperty(name = "seed.delivery.endpoint")
    String seedDeliveryEndpoint;

    @Override
    public void configure() throws Exception {
        from("sjms2:" + tickEndpoint)
                .log(LoggingLevel.INFO, "Received tick message: ${body}")
                .unmarshal().json(TickMessageDTO.class)
                .process(tickProcessor)
                .process(seedProcessor)
                .split(body())
                .to(seedDeliveryEndpoint)
                .log(LoggingLevel.INFO, "Tick message processed successfully.");
    }
}
