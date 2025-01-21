package fr.pantheonsorbonne.camel.processor;

import fr.pantheonsorbonne.service.ResourceInitializerService;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class InitProcessor implements Processor {
    @Inject
    ResourceInitializerService resourceInitializerService;
    @Override
    public void process(Exchange exchange) throws Exception {
        Double money = exchange.getIn().getBody(Double.class);
        resourceInitializerService.initializeResources(money);
    }
}
