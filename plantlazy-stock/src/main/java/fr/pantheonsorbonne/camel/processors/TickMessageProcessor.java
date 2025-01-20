package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.service.StockService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class TickMessageProcessor implements Processor {

    @Inject
    StockService stockService;

    @Override
    public void process(Exchange exchange) throws Exception {
        TickMessage tickMessage = exchange.getIn().getBody(TickMessage.class);

        stockService.refillDailyResource();
    }
}