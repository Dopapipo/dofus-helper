package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.services.PlantService;
import fr.pantheonsorbonne.services.SeedService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TickSubscriber extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    @Inject
    SeedService seedService;

    @Inject
    PlantService plantService;

    @Override
    public void configure() {
        from("sjms2:topic:" + tickEndpoint)
                .unmarshal().json(JsonLibrary.Jackson, TickMessage.class)
                .threads(1)
                .choice()
                .when(simple("${body.tickType} == 'DAILY'"))

                .process(exchange -> {
                    seedService.updateDailySeedOffer();
                    seedService.sellSeedsDaily();
                })
                .when(simple("${body.tickType} == 'HOURLY'"))
                .process(exchange -> {
                    plantService.sellPlants();
                })

                .otherwise()
                .log("Unknown TickType: ${body.tickType}")
                .endChoice();
    }
}
