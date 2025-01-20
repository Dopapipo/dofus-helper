package fr.pantheonsorbonne.camel.consumers;

import fr.pantheonsorbonne.camel.processors.StockMessageProcessor;
import fr.pantheonsorbonne.dto.ResourceDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class StockConsumer extends RouteBuilder {

    @Inject
    StockMessageProcessor stockMessageProcessor;

    @Override
    public void configure() throws Exception {
        from("sjms2:stock")
                .unmarshal().json(ResourceDTO.class)
                .process(stockMessageProcessor);

    }
}