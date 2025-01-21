package fr.pantheonsorbonne.camel.processors;


import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.service.PlantService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class TickProcessor implements Processor {

    @Inject
    PlantService plantService;

    @Override
    public void process(Exchange exchange) {
        TickMessage messageBody = exchange.getIn().getBody(TickMessage.class);
        if (messageBody.getTickType() == TickType.HOURLY) {
            plantService.processHouryPlantLifecycle();
        }
        if(messageBody.getTickType() == TickType.DAILY){
            plantService.processDailyCycle();
        }
    }
}
