package fr.pantheonsorbonne.camel.consumers;
import fr.pantheonsorbonne.camel.processors.TickProcessor;
import fr.pantheonsorbonne.dto.TickMessage;
import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TickSubscriber extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    @Inject
    TickProcessor tickProcessor;
    @Override
    public void configure() throws Exception {
        from("sjms2:topic:" + tickEndpoint)
                .log("Received tick message")
                .unmarshal().json(JsonLibrary.Jackson, TickMessage.class)
                .process(tickProcessor);

    }

}
