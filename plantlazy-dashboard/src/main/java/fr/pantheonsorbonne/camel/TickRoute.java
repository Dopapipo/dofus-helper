package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.camel.processor.TickProcessor;
import fr.pantheonsorbonne.entity.TickMessage;
import fr.pantheonsorbonne.model.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TickRoute extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;


    @Override
    public void configure() {
        from("sjms2:topic:" + tickEndpoint)
                .log("Received tick message from topic: " + tickEndpoint)
                .unmarshal().json(JsonLibrary.Jackson, TickMessage.class)
                .process(new TickProcessor());
    }
}
