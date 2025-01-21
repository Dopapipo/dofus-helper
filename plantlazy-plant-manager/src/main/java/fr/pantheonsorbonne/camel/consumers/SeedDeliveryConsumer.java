
package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.SeedProcessor;
import fr.pantheonsorbonne.dto.SeedPriceResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SeedDeliveryConsumer extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "seeddelivery.endpoint")
    String seedDeliveryEndpoint;

    @Inject
    SeedProcessor seedProcessor;

    @Override
    public void configure() throws Exception {
        from(seedDeliveryEndpoint)
                .log(LoggingLevel.INFO, "Received seed delivery: ${body}")
                .unmarshal().json(SeedPriceResponseDTO.class)
                .process(seedProcessor)
                .log(LoggingLevel.INFO, "Seed delivery processed successfully.");
    }
}
