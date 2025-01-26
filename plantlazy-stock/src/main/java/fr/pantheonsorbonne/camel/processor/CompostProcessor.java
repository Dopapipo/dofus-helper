
package fr.pantheonsorbonne.camel.processor;

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
    public void process(Exchange exchange) {
        DeadPlantDTO deadPlant = exchange.getIn().getBody(DeadPlantDTO.class);
        compostService.processDeadPlant(deadPlant);
        }
}
