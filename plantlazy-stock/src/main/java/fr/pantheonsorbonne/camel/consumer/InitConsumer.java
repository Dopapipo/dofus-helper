package fr.pantheonsorbonne.camel.consumer;

import fr.pantheonsorbonne.camel.processor.InitProcessor;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
@ApplicationScoped
public class InitConsumer extends RouteBuilder {

    @ConfigProperty(name = "init.endpoint")
    String initEndpoint;


    @Override
    public void configure() {
        from(initEndpoint)
                .process(new InitProcessor());
    }
}