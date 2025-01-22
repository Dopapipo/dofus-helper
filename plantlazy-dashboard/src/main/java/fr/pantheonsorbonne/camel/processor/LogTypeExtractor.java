package fr.pantheonsorbonne.camel.processor;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class LogTypeExtractor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        String logType = extractLogType(body);
        exchange.getIn().setHeader("logType", logType);
    }

    private String extractLogType(String body) {
        if (body.contains("\"DEAD_PLANT_UPDATE\"")) return "DEAD_PLANT_UPDATE";
        if (body.contains("\"PLANT_CREATED\"")) return "PLANT_CREATED";
        if (body.contains("\"PLANT_DEAD\"")) return "PLANT_DEAD";
        if (body.contains("\"PLANT_GROWN\"")) return "PLANT_GROWN";
        if (body.contains("\"PLANT_UPDATE\"")) return "PLANT_UPDATE";
        if (body.contains("\"RESOURCE_UPDATE\"")) return "RESOURCE_UPDATE";
        if (body.contains("\"STORE_SELLABLE_PLANTS\"")) return "STORE_SELLABLE_PLANTS";
        if (body.contains("\"STORE_SOLD_PLANT\"")) return "STORE_SOLD_PLANT";
        if (body.contains("\"STORE_SELLABLE_SEEDS\"")) return "STORE_SELLABLE_SEEDS";
        return null;
    }
}
