
package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dao.PlantDAO;
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
        compostService.processDeadPlant();
        }
}
