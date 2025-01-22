package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.camel.processor.LogTypeExtractor;
import fr.pantheonsorbonne.dto.log.DeadPlantLogDTO;
import fr.pantheonsorbonne.dto.log.PlantCreatedLogDTO;
import fr.pantheonsorbonne.dto.log.PlantDeadLogDTO;
import fr.pantheonsorbonne.dto.log.PlantGrownLogDTO;
import fr.pantheonsorbonne.dto.log.PlantUpdateLogDTO;
import fr.pantheonsorbonne.dto.log.ResourceUpdateLogDTO;
import fr.pantheonsorbonne.dto.log.StoreSellablePlantLogDTO;
import fr.pantheonsorbonne.dto.log.StoreSellableSeedsLogDTO;
import fr.pantheonsorbonne.dto.log.StoreSoldPlantLogDTO;
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

        from("file:data/log?noop=true")
                .convertBodyTo(String.class)
                .log("Received log entry: ${body}")
                .process(new LogTypeExtractor()) // Extrait le type de log et l'ajoute à l'header "logType"
                .choice()
                .when(header("logType").isNotNull())
                .toD("direct:${header.logType}") // Envoie dynamiquement vers la sous-route correspondante
                .otherwise()
                .log("Unsupported log type: ${body}");

        /* === Sous-routes basées sur le type de log === */
        configureLogRoute("DEAD_PLANT_UPDATE", DeadPlantLogDTO.class, "processDeadPlant");
        configureLogRoute("PLANT_CREATED", PlantCreatedLogDTO.class, "processPlantCreated");
        configureLogRoute("PLANT_DEAD", PlantDeadLogDTO.class, "processPlantDead");
        configureLogRoute("PLANT_GROWN", PlantGrownLogDTO.class, "processPlantGrown");
        configureLogRoute("PLANT_UPDATE", PlantUpdateLogDTO.class, "processPlantUpdate");
        configureLogRoute("RESOURCE_UPDATE", ResourceUpdateLogDTO.class, "processResourceUpdate");
        configureLogRoute("STORE_SELLABLE_PLANTS", StoreSellablePlantLogDTO.class, "processStoreSellablePlant");
        configureLogRoute("STORE_SOLD_PLANT", StoreSoldPlantLogDTO.class, "processStoreSoldPlant");
        configureLogRoute("STORE_SELLABLE_SEEDS", StoreSellableSeedsLogDTO.class, "processStoreSellableSeeds");

    }

    private void configureLogRoute(String logType, Class<?> dtoClass, String dashboardMethod) {
        from("direct:" + logType)
                .unmarshal().json(JsonLibrary.Jackson, dtoClass)
                .log("Processing " + logType + ": ${body}")
                .bean("eventLogService", "saveEventLog")
                .bean("dashboardService", dashboardMethod);
    }
}
