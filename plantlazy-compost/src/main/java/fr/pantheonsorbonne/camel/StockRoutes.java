package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.camel.processor.TickMessageProcessor;
import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.service.StockService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
    public void configure() throws Exception {
        from("direct:resourceUpdateRoute")
                .marshal().json()
                .to("sjms2:queue:log");

        from(tickEndpoint)
                .process(new TickMessageProcessor())
                .filter(exchange -> exchange.getIn().getBody(TickMessage.class) != null)
                .filter(exchange -> exchange.getIn().getBody(TickMessage.class).getTickType() == TickType.DAILY)
                .bean(stockService, "refillDailyResources");
    }
}





