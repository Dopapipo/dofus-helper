package fr.pantheonsorbonne.camel.processor;


import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.services.SeedService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class TickProcessor implements Processor {

    @Inject
    SeedService seedService;

    @Override
    public void process(Exchange exchange) {
        TickMessage messageBody = exchange.getIn().getBody(TickMessage.class);
        if (messageBody.getTickType() == TickType.DAILY) {
            seedService.updateDailySeedOffer();
        }
    }
}

