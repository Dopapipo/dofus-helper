package fr.pantheonsorbonne.camel.processor;

import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.services.PlantService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class SellPlantProcessor implements Processor {

    @Inject
    PlantService plantService;

    @Override
    public void process(Exchange exchange) throws Exception {
        PlantSaleDTO plantDTO = exchange.getIn().getBody(PlantSaleDTO.class);
        plantService.sellPlant(plantDTO);
    }
}
