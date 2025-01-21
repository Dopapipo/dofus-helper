package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.InitProcessor;
import fr.pantheonsorbonne.dto.InitRequestDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class InitConsumer extends RouteBuilder {

    @ConfigProperty(name = "init.endpoint")
    String initEndpoint;


    @Override
    public void configure() {
        from(initEndpoint)
                .log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa${body}")
                .process(new InitProcessor());
    }
}
