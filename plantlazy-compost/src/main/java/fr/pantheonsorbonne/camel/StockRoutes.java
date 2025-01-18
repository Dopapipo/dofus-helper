package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.service.StockService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class StockRoutes extends RouteBuilder {
    private final StockService stockService;

    @Inject
    public StockRoutes(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public void configure() throws Exception {
        from("direct:resourceUpdateRoute")
                .marshal().json()
                .to("sjms2:queue:log");

        from("tick.endpoint")
                .filter(body().isNotNull())
                .choice()
                .when(body().contains("DAILY"))
                .bean(stockService, "RefillDailyResources")
                .end();
    }


}





