package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.dto.log.*;
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
                .convertBodyTo(String.class)
                .choice()

                .when(simple("${body} contains '\"DEAD_PLANT_UPDATE\"'"))
                .unmarshal().json(JsonLibrary.Jackson, DeadPlantLogDTO.class)
                .log("Processing DEAD_PLANT_UPDATE: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processDeadPlant")

                .when(simple("${body} contains '\"PLANT_CREATED\"'"))
                .unmarshal().json(JsonLibrary.Jackson, PlantCreatedLogDTO.class)
                .log("Processing PLANT_CREATED: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processPlantCreated")

                .when(simple("${body} contains '\"PLANT_DEAD\"'"))
                .unmarshal().json(JsonLibrary.Jackson, PlantDeadLogDTO.class)
                .log("Processing PLANT_DEAD: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processPlantDead")

                .when(simple("${body} contains '\"PLANT_GROWN\"'"))
                .unmarshal().json(JsonLibrary.Jackson, PlantGrownLogDTO.class)
                .log("Processing PLANT_GROWN: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processPlantGrown")

                .when(simple("${body} contains '\"PLANT_UPDATE\"'"))
                .unmarshal().json(JsonLibrary.Jackson, PlantUpdateLogDTO.class)
                .log("Processing PLANT_UPDATE: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processPlantUpdate")

                .when(simple("${body} contains '\"RESOURCE_UPDATE\"'"))
                .unmarshal().json(JsonLibrary.Jackson, ResourceUpdateLogDTO.class)
                .log("Processing RESOURCE_UPDATE: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processResourceUpdate")

                .when(simple("${body} contains '\"STORE_SELLABLE_PLANTS\"'"))
                .unmarshal().json(JsonLibrary.Jackson, StoreSellablePlantLogDTO.class)
                .log("Processing STORE_SELLABLE_PLANTS: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processStoreSellablePlant")

                .when(simple("${body} contains '\"STORE_SOLD_PLANT\"'"))
                .unmarshal().json(JsonLibrary.Jackson, StoreSoldPlantLogDTO.class)
                .log("Processing STORE_SOLD_PLANT: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processStoreSoldPlant")

                .when(simple("${body} contains '\"STORE_SELLABLE_SEEDS\"'"))
                .unmarshal().json(JsonLibrary.Jackson, StoreSellableSeedsLogDTO.class)
                .log("Processing STORE_SELLABLE_SEEDS: ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", "processStoreSellableSeeds")

                .otherwise()
                .log("Unsupported log type: ${body}");
    }
}
