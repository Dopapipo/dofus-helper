package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class LogsRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("file:data/log?noop=true") // Lecture des fichiers JSON depuis le système de fichiers
                .convertBodyTo(String.class) // Convertit le fichier en chaîne JSON
                .choice()

                // RESOURCE_UPDATE
                .when(simple("${body} contains 'RESOURCE_UPDATE'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.ResourceUpdateDTO.class)
                .log("Processing resource update: ${body}")
                .bean("dashboardService", "processResourceUpdate")

                // STORE_SELLABLE_SEEDS
                .when(simple("${body} contains 'STORE_SELLABLE_SEEDS'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.SeedSaleUpdateDTO.class)
                .log("Processing store sellable seeds: ${body}")
                .bean("dashboardService", "processStoreSellableSeeds")

                // STORE_SELLABLE_PLANTS
                .when(simple("${body} contains 'STORE_SELLABLE_PLANTS'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.PlantSaleUpdateDTO.class)
                .log("Processing store sellable plants: ${body}")
                .bean("dashboardService", "processStoreSellablePlants")

                // DEAD_PLANT_UPDATE
                .when(simple("${body} contains 'DEAD_PLANT_UPDATE'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.DeadPlantUpdateDTO.class)
                .log("Processing dead plant update: ${body}")
                .bean("dashboardService", "processDeadPlantUpdate")

                // PLANT_UPDATE
                .when(simple("${body} contains 'PLANT_UPDATE'"))
                .unmarshal().json(JsonLibrary.Jackson, fr.pantheonsorbonne.dto.update.PlantUpdateDTO.class)
                .log("Processing plant update: ${body}")
                .bean("dashboardService", "processPlantUpdate")

                // Unsupported log types
                .otherwise()
                .log("Unsupported log type: ${body}");









    }
}
