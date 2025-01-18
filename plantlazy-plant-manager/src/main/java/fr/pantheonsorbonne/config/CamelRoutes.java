
package fr.pantheonsorbonne.config;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Route for seed prices
        from("sjms2:plantlazy.seed.price")
            .routeId("seedPriceRoute")
            .log("Received seed price: ${body}")
            .to("bean:seedPriceService?method=processSeedPrice");

        // Route for seeds
        from("sjms2:plantlazy.seeds")
            .routeId("seedRoute")
            .log("Received seeds: ${body}")
            .to("bean:seedService?method=processSeeds");

        // Route for money
        from("sjms2:plantlazy.money")
            .routeId("moneyRoute")
            .log("Received money: ${body}")
            .to("bean:moneyService?method=processMoney");

        // Route for dead plants
        from("sjms2:plantlazy.dead.plants")
            .routeId("deadPlantRoute")
            .log("Received dead plant: ${body}")
            .to("bean:deadPlantService?method=processDeadPlant");
    }
}
