package fr.pantheonsorbonne.camel.processor;

import fr.pantheonsorbonne.dto.PlantFromFarmDTO;
import fr.pantheonsorbonne.services.PlantService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class PlantProcessor implements Processor {

    @Inject
    PlantService plantService;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        PlantFromFarmDTO plantDTO = exchange.getIn().getBody(PlantFromFarmDTO.class);

        plantService.putPlantInShop(plantDTO);

    }
}
