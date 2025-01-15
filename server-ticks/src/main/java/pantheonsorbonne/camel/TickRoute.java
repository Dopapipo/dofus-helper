package pantheonsorbonne.camel;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import pantheonsorbonne.entity.TickType;


@ApplicationScoped
public class TickRoute extends RouteBuilder {

    @Inject
    TickProducer tickProducer;

    @Override
    public void configure() throws Exception {
        // Route for hourly ticks
        from("timer:hourly?delay=0&period={{tick.hourly.interval}}")
                .process(exchange -> tickProducer.sendTick(TickType.HOURLY));

        // Route for daily ticks
        from("timer:daily?delay=86400000&period=86400000")
                .process(exchange -> tickProducer.sendTick(TickType.DAILY));
    }
}


