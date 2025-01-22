package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processor.SavePlantProcessor;
import fr.pantheonsorbonne.dto.PlantSaleDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PlantDataConsumer extends RouteBuilder {

    @ConfigProperty(name = "store.plant.transport.endpoint")
    String plantSale;

    @Inject
    SavePlantProcessor savePlantProcessor;

    @Override
    public void configure() {
        from("file:data/plant?noop=true")
                .log("{$body}")
                .unmarshal().json(PlantSaleDTO.class)
                .process(savePlantProcessor);
    }
}
