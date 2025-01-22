package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.services.PlantService;
import fr.pantheonsorbonne.services.SeedService;
import fr.pantheonsorbonne.services.SendingSeedService;
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
    SeedService seedService;

    @Inject
    PlantService plantService;

    @Inject
    SendingSeedService sendingSeedService;

    @Override
    public void configure() {
        from(tickEndpoint)
                .unmarshal().json(JsonLibrary.Jackson, TickMessage.class)
                .process(exchange -> {
                    TickMessage messageBody = exchange.getIn().getBody(TickMessage.class);
                    if (messageBody.getTickType() == TickType.DAILY) {
                        seedService.updateDailySeedOffer();

                        sendingSeedService.sendAllSeedsToQueue();

                    }
                    if (messageBody.getTickType() == TickType.HOURLY) {
                        plantService.sellPlants();
                    }
                });


    }

}
