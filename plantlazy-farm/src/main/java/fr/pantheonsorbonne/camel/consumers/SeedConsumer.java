package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.SeedProcessor;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.exception.SeedGrowException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

// Consomme les graines et les transforme en plantes
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
                .onException(SeedGrowException.class).to("direct:logQueue")
                .to("direct:logQueue")
        ;
    }
}
