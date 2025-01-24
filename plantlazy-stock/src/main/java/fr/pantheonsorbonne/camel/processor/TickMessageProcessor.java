package fr.pantheonsorbonne.camel.processor;

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
    public void process(Exchange exchange) {

        stockService.refillDailyResource();

    }
}