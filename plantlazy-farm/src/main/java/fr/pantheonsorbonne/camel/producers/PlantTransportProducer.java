package fr.pantheonsorbonne.camel.producers;

import fr.pantheonsorbonne.camel.processors.DeadPlantProcessor;
import fr.pantheonsorbonne.camel.processors.SoldPlantProcessor;
import fr.pantheonsorbonne.dto.PlantDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantTransportProducer extends RouteBuilder {
    @ConfigProperty(name = "dead.plant.transport.endpoint")
    String transportEndpoint;

    @ConfigProperty(name = "store.plant.transport.endpoint")
    String storePlantEndpoint;

    @Inject
    DeadPlantProcessor deadPlantProcessor;

    @Inject
    SoldPlantProcessor soldPlantProcessor;

    @Override
    public void configure() throws Exception {
        from("direct:plantQueue")
                .log("Plante morte ou mature")
                .log("${body}")

                .choice()
                .when(header("dead").isEqualTo(true))
                .process(deadPlantProcessor).to(transportEndpoint)

                .when(header("sold").isEqualTo(true))
                .process(soldPlantProcessor).to(storePlantEndpoint);
    }
}


