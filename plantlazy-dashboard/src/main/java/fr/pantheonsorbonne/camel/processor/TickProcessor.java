package fr.pantheonsorbonne.camel.processor;


import fr.pantheonsorbonne.entity.TickMessage;
import fr.pantheonsorbonne.service.DashboardService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class TickProcessor implements Processor {

    @Inject
    DashboardService dashboardService;

    @Override
    public void process(Exchange exchange) {
        TickMessage messageBody = exchange.getIn().getBody(TickMessage.class);
        dashboardService.processTick(messageBody);
    }
}

