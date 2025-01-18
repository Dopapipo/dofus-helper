
package fr.pantheonsorbonne.plantlazy.manager.service;

import org.apache.camel.ProducerTemplate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeadPlantsConsumerService {

    @Inject
    private ProducerTemplate producerTemplate;

    public void processDeadPlantMessage(String message) {
        System.out.println("Received dead plant message: " + message);

        // Simulate transforming the dead plant into compost
        String compost = transformToCompost(message);

        // Send the compost to plantlazy-compost via Camel route
        String compostQueue = "direct:compost-queue";
        producerTemplate.sendBody(compostQueue, compost);
        System.out.println("Compost sent: " + compost);
    }

    private String transformToCompost(String deadPlant) {
        // Example transformation logic
        return "Compost generated from: " + deadPlant;
    }
}
