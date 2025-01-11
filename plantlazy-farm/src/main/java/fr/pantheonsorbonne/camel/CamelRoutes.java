package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.camel.processors.SeedProcessor;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("sjms2:plantlazy.seeds")
                .process(new SeedProcessor())
    }
}
