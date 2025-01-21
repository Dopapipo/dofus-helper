package fr.pantheonsorbonne.camel.consumers;
import fr.pantheonsorbonne.camel.processors.PlantLifecycleProcessor;
import fr.pantheonsorbonne.dto.TickMessage;
import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantLifecycleConsumer extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    @Inject
    PlantLifecycleProcessor plantLifecycleProcessor;
    @Override
    public void configure() throws Exception {
        from(tickEndpoint)
                .log(LoggingLevel.INFO, "Received message from ${header.CamelJmsMessageID}: ${body}")

                .unmarshal().json(JsonLibrary.Jackson, TickMessage.class)

                .process(plantLifecycleProcessor)

                .log(LoggingLevel.INFO, "Message processed successfully.");
    }

}
