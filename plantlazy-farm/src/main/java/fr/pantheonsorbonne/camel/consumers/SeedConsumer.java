package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.SeedProcessor;
import fr.pantheonsorbonne.dto.SeedDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;


@ApplicationScoped
public class SeedConsumer extends RouteBuilder {
    @Inject
    SeedProcessor seedProcessor;
    @Override
    public void configure() throws Exception {
        from("sjms2:plantlazy.seeds")
                .unmarshal().json(SeedDTO.class)
                .process(seedProcessor) ;
    }
}
