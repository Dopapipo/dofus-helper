package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.camel.processor.TickMessageProcessor;
import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.service.StockService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class StockRoutes extends RouteBuilder {
    private final StockService stockService;

    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    @Inject
    public StockRoutes(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public void configure() {
        from(tickEndpoint)
                .log(LoggingLevel.INFO, "Static log to test")
                .process(new TickMessageProcessor())
                .filter(exchange -> exchange.getIn().getBody(TickMessage.class) != null)
                .filter(exchange -> exchange.getIn().getBody(TickMessage.class).getTickType() == TickType.DAILY)
                .log(LoggingLevel.INFO, "Processing daily tick: ${body}")
                .bean(stockService, "refillDailyResources");

    }
}





