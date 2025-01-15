package pantheonsorbonne.camel;
import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TickRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:hourly?delay=0&period={{tick.hourly.interval}}")
                .bean("tickProducer", "sendTick('HOURLY')");

        from("timer:daily?delay=86400000&period=86400000")
                .bean("tickProducer", "sendTick('DAILY')");

    }
}

