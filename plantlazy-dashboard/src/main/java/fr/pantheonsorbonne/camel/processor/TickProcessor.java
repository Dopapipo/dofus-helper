package fr.pantheonsorbonne.camel.processor;


import fr.pantheonsorbonne.entity.TickMessage;
import fr.pantheonsorbonne.service.DashboardService;
import io.quarkus.arc.Arc;
import io.quarkus.arc.InstanceHandle;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class TickProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        TickMessage messageBody = exchange.getIn().getBody(TickMessage.class);

        InstanceHandle<DashboardService> serviceHandle = Arc.container().instance(DashboardService.class);
        DashboardService dashboardService = serviceHandle.get();

        dashboardService.processTick(messageBody);
    }
}

