package fr.pantheonsorbonne.camel.processor;


import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.services.PlantService;
import fr.pantheonsorbonne.services.SeedService;
import fr.pantheonsorbonne.services.SendingSeedService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
@Transactional
public class TickProcessor implements Processor {

    @Inject
    SeedService seedService;

    @Inject
    PlantService plantService;

    @Inject
    SendingSeedService sendingSeedService;

    @Override
    public void process(Exchange exchange) {
        TickMessage messageBody = exchange.getIn().getBody(TickMessage.class);
        if (messageBody.getTickType() == TickType.DAILY) {
            seedService.updateDailySeedOffer();
            sendingSeedService.sendAllSeedsToQueue();

        }
        if (messageBody.getTickType() == TickType.HOURLY) {
            plantService.sellPlants();
        }
    }
}

