package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.ResourceDTO;
import fr.pantheonsorbonne.entity.ResourceType;
import fr.pantheonsorbonne.service.StockService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class StockMessageProcessor implements Processor {

    @Inject
    StockService stockService;

    @Override
    public void process(Exchange exchange) throws Exception {
        ResourceDTO resourceDTO = exchange.getIn().getBody(ResourceDTO.class);

        String operationTag = resourceDTO.getOperationTag();
        if (operationTag == null || (!operationTag.equals("STOCK_RECEIVED") && !operationTag.equals("STOCK_QUERIED"))) {
            throw new IllegalArgumentException("Invalid operation tag. Must be 'STOCK_RECEIVED' or 'STOCK_QUERIED'.");
        }

        ResourceType type = resourceDTO.getType();
        Double quantity = resourceDTO.getQuantity();

        if (operationTag.equals("STOCK_RECEIVED")) {
            stockService.updateResource(type, quantity, operationTag);
        } else {
            stockService.updateResource(type, -quantity, operationTag);
        }
    }
}