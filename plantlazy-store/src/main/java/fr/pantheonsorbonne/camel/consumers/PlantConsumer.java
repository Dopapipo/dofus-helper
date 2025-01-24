package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processor.PlantProcessor;
import fr.pantheonsorbonne.dto.PlantFromFarmDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantConsumer extends RouteBuilder {

    @ConfigProperty(name = "store.plant.transport.endpoint")
    String plantToShop;

    @Inject
    PlantProcessor plantProcessor;

    @Override
    public void configure() {
        from(plantToShop)
                .log("{$body}")
                .unmarshal().json(PlantFromFarmDTO.class)
                .process(plantProcessor);
    }
}
