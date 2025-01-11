package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.camel.processors.SeedProcessor;
import fr.pantheonsorbonne.dto.SeedDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("sjms2:plantlazy.seeds") // Receive messages from the JMS queue
                .unmarshal().json(SeedDTO.class) // Convert JSON payload to SeedDTO
                .process(new SeedProcessor()) // Process the DTO
                .to("bean:seedService?method=saveSeed"); // Call the SeedService to persist
    }}
