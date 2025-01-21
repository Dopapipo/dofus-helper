
package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.CompostProcessor;
import fr.pantheonsorbonne.dto.DeadPlantDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class DeadPlantConsumer extends RouteBuilder {

    @Inject
    @ConfigProperty(name = "plant.delivery.endpoint")
    String deadPlantEndpoint;

    @Inject
    CompostProcessor compostProcessor;

    @Override
    public void configure() throws Exception {
        from(deadPlantEndpoint)
                .unmarshal().json(DeadPlantDTO.class)
                .process(compostProcessor);
    }
}
