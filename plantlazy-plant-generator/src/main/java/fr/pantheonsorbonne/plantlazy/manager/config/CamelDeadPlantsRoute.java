
package fr.pantheonsorbonne.plantlazy.manager.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import fr.pantheonsorbonne.plantlazy.manager.service.DeadPlantsConsumerService;
import jakarta.inject.Inject;

@Component
public class CamelDeadPlantsRoute extends RouteBuilder {

    @Inject
    private DeadPlantsConsumerService deadPlantsConsumerService;

    @Override
    public void configure() throws Exception {
        from("jms:queue:dead-plants-queue")
            .log("Message received from dead-plants-queue: ${body}")
            .bean(deadPlantsConsumerService, "processDeadPlantMessage");

        from("direct:compost-queue")
            .log("Sending compost to plantlazy-compost: ${body}");
    }
}
