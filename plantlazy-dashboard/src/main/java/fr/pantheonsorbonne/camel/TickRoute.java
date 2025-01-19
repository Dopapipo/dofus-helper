package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.entity.TickMessage;
import fr.pantheonsorbonne.model.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

@ApplicationScoped
public class TickRoute extends RouteBuilder {

    private final Dashboard dashboard = new Dashboard();


    @Override
    public void configure() {

        from("file:data/tick?noop=true")

                // from("sjms2:serverTicks")
                .unmarshal().json(JsonLibrary.Jackson, TickMessage.class)
                .log("Processing tick: ${body}")
                .bean("dashboardService", "processTick");

    }
}
