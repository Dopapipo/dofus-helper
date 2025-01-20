package fr.pantheonsorbonne.camel.producers;

import fr.pantheonsorbonne.dto.LogMessage;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class LogProducer  extends RouteBuilder {
    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;
    @Override
    public void configure() throws Exception {
        from("direct:logQueue")
                .log("Sending log message: ${body}")
                .marshal().json(LogMessage.class)
                .to(logEndpoint);
    }
}
