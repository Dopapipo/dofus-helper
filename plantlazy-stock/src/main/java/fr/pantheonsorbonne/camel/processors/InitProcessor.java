package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.InitRequestDTO;
import fr.pantheonsorbonne.service.ResourceInitializerService;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InitProcessor implements Processor {
    @Inject
    ResourceInitializerService resourceInitializerService;
    @Override
    public void process(Exchange exchange) throws Exception {
        InitRequestDTO money = exchange.getIn().getBody(InitRequestDTO.class);
        resourceInitializerService.initializeResources(money);
    }
}
