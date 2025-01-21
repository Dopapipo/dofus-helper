package fr.pantheonsorbonne.camel.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.InitMoneyDTO;
import fr.pantheonsorbonne.service.ResourceInitializerService;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InitProcessor implements Processor {
    @Inject
    ResourceInitializerService resourceInitializerService;
    @Inject
    ObjectMapper objectMapper;
    @Override
    public void process(Exchange exchange) throws Exception {
        InitMoneyDTO money = objectMapper.readValue(exchange.getIn().getBody(String.class), InitMoneyDTO.class);
        resourceInitializerService.initializeResources(money);
    }
}
