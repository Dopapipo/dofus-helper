
package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.DeadPlantDTO;
import fr.pantheonsorbonne.service.CompostService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class CompostProcessor implements Processor {

    @Inject
    CompostService compostService;

    @Override
    public void process(Exchange exchange) throws Exception {
        DeadPlantDTO deadPlant = exchange.getIn().getBody(DeadPlantDTO.class);
        int compostQuantity = compostService.transformToCompost(deadPlant);
        System.out.println("Processed dead plant to compost: " + compostQuantity + " units.");
    }
}
