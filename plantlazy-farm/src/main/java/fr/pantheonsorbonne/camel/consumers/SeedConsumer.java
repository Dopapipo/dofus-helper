package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.SeedProcessor;
import fr.pantheonsorbonne.dto.SeedDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class SeedConsumer extends RouteBuilder {
    @Inject
    SeedProcessor seedProcessor;
    @ConfigProperty(name = "plant.seed.endpoint")
    String seedEndpoint;
    @Override
    public void configure() throws Exception {
        from(seedEndpoint)
                .unmarshal().json(SeedDTO.class)
                .process(seedProcessor)
                .to("direct:logQueue")
        ;
    }
}
