package fr.pantheonsorbonne.camel.processor;

import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.services.PlantService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class SavePlantProcessor implements Processor {

    @Inject
    PlantService plantService;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {
        // Récupérer le PlantSaleDTO depuis le corps de l'échange

        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBb");

        PlantSaleDTO plantDTO = exchange.getIn().getBody(PlantSaleDTO.class);

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Appeler le service pour effectuer la vente
        plantService.savePlant(plantDTO);
    }
}
