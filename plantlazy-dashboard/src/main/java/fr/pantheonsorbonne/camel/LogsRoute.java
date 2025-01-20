package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.dto.update.LivePlantUpdateDTO;
import fr.pantheonsorbonne.dto.update.LivePlantCreatedUpdateDTO;
import fr.pantheonsorbonne.dto.update.LivePlantGrownUpdateDTO;
import fr.pantheonsorbonne.dto.update.LivePlantDeletedUpdateDTO;
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
        // from(logEndpoint)
        from("file:data/log?noop=true")
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
                .unmarshal().json(JsonLibrary.Jackson, LivePlantUpdateDTO.class)
                .log("Processing plant update: ${body}")
                .bean("dashboardService", "processPlantUpdate")


                .when(simple("${body} contains 'PLANT_UPDATE'"))
                .unmarshal().json(JsonLibrary.Jackson, LivePlantUpdateDTO.class)
                .log("Processing plant update: ${body}")
                .bean("dashboardService", "processPlantUpdate")

                .when(simple("${body} contains 'PLANT_DEAD'"))
                .unmarshal().json(JsonLibrary.Jackson, LivePlantDeletedUpdateDTO.class)
                .log("Processing plant deleted: ${body}")
                .bean("dashboardService", "processPlantDeleted")

                .when(simple("${body} contains 'PLANT_GROWN'"))
                .unmarshal().json(JsonLibrary.Jackson, LivePlantGrownUpdateDTO.class)
                .log("Processing plant grown: ${body}")
                .bean("dashboardService", "processPlantGrown")

                .when(simple("${body} contains 'PLANT_CREATED'"))
                .unmarshal().json(JsonLibrary.Jackson, LivePlantCreatedUpdateDTO.class)
                .log("Processing plant created: ${body}")
                .bean("dashboardService", "processPlantCreated")



                // Log non reconnu
                .otherwise()
                .log("Unsupported log type: ${body}");









    }
}
