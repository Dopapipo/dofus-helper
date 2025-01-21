package fr.pantheonsorbonne.camel.producers;

import fr.pantheonsorbonne.camel.processors.DeadPlantProcessor;
import fr.pantheonsorbonne.camel.processors.SoldPlantProcessor;
import fr.pantheonsorbonne.dto.PlantDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantTransportProducer extends RouteBuilder {
    @ConfigProperty(name = "dead.plant.transport.endpoint")
    String transportEndpoint;
    @ConfigProperty(name = "store.plant.transport.endpoint")
    String storePlantEndpoint;

    @Override
    public void configure() throws Exception {
        from("direct:plantQueue")
                .marshal().json(PlantDTO.class).choice()
                .when(header("dead").isEqualTo(true)).process(new DeadPlantProcessor()).to(transportEndpoint)
                .otherwise().when(header("sold").isEqualTo(false)).process(new SoldPlantProcessor()).to(storePlantEndpoint);
    }
}

