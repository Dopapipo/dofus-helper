package fr.pantheonsorbonne.camel.producers;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

// Produit des logs pour le microservice dashboard
@ApplicationScoped
public class LogProducer  extends RouteBuilder {
    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;
    @Override
    public void configure() {
        from("direct:logQueue")
                .marshal().json()
                .to(logEndpoint);
    }
}
