package fr.pantheonsorbonne.camel.producers;

import fr.pantheonsorbonne.dto.PlantDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantTransportProducer extends RouteBuilder {
    @ConfigProperty(name = "dead.plant.transport.endpoint")
    String deadPlantTransportEndpoint;
    @ConfigProperty(name = "store.plant.transport.endpoint")
    String storeTransportEndpoint;

    @Override
    public void configure() throws Exception {
        from("direct:plantQueue")
                .choice()
                .when(header("dead").isEqualTo(true))
                .marshal().json(PlantDTO.class)
                .to(deadPlantTransportEndpoint)
        .otherwise()
                .marshal().json(PlantDTO.class)
                .to(storeTransportEndpoint);
    }
}

