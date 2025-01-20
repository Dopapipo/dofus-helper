package fr.pantheonsorbonne.camel;

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


    private final Dashboard dashboard = new Dashboard();


    @Override
    public void configure() {
        // from(tickEndpoint)
        from("file:data/tick?noop=true")
                .unmarshal().json(JsonLibrary.Jackson, TickMessage.class)
                .log("Processing tick: ${body}")
                .bean("dashboardService", "processTick");

    }
}
