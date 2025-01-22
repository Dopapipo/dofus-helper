package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processor.TickProcessor;
import fr.pantheonsorbonne.dto.TickMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TickConsumer extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    @Inject
    TickProcessor tickProcessor;

    @Override
    public void configure() throws Exception {
        from(tickEndpoint)
                .log(LoggingLevel.INFO, "Received message from ${header.CamelJmsMessageID}: ${body}")

                .unmarshal().json(JsonLibrary.Jackson, TickMessage.class)

                .process(tickProcessor)

                .log(LoggingLevel.INFO, "Message processed successfully.");
    }

}
