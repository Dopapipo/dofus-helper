package fr.pantheonsorbonne.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class LogsRoute extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;


    @Override
    public void configure() {
        from(logEndpoint)
                .convertBodyTo(String.class) // Convertit le fichier en chaîne JSON
                .choice()

                // Update des ressources
                .when(simple("${body} contains 'RESOURCE_UPDATE'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.ResourceUpdateDTO.class)
                .log("Processing resource update: ${body}")
                .bean("dashboardService", "processResourceUpdate")

                // Update des graines en vente
                .when(simple("${body} contains 'STORE_SELLABLE_SEEDS'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.SeedSaleUpdateDTO.class)
                .log("Processing store sellable seeds: ${body}")
                .bean("dashboardService", "processStoreSellableSeeds")

                // Update des plantes en vente
                .when(simple("${body} contains 'STORE_SELLABLE_PLANTS'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.PlantSaleUpdateDTO.class)
                .log("Processing store sellable plants: ${body}")
                .bean("dashboardService", "processStoreSellablePlants")

                // Update des plantes mortes
                .when(simple("${body} contains 'DEAD_PLANT_UPDATE'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.DeadPlantUpdateDTO.class)
                .log("Processing dead plant update: ${body}")
                .bean("dashboardService", "processDeadPlantUpdate")

                // Update des plantes
                .when(simple("${body} contains 'PLANT_UPDATE'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.PlantUpdateDTO.class)
                .log("Processing plant update: ${body}")
                .bean("dashboardService", "processPlantUpdate")

                // Log non reconnu
                .otherwise()
                .log("Unsupported log type: ${body}");









    }
}
