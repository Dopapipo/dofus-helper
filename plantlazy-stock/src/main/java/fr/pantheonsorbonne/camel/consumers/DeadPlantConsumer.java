
package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.CompostProcessor;
import fr.pantheonsorbonne.dto.DeadPlantDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class DeadPlantConsumer extends RouteBuilder {

    @ConfigProperty(name = "dead.plant.transport.endpoint")
    String deadPlantEndpoint;

    @Inject
    CompostProcessor compostProcessor;

    @Override
    public void configure() {
        from(deadPlantEndpoint)
                .unmarshal().json(DeadPlantDTO.class)
                .process(compostProcessor);
    }
}
