package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import fr.pantheonsorbonne.dto.TickDTO;

@ApplicationScoped
public class TickRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("timer://tick?period=5000")
                .process(exchange -> {
                    TickDTO tick = new TickDTO("TICK");
                    exchange.getIn().setBody(tick);
                })
                .marshal().json()
                // .to("sjms2:queue:TICK_QUEUE")
                // .log("Tick envoyé à la queue TICK_QUEUE: ${body}");
                .to("file://tick?fileName=ticks.json")
                .to("file://log?fileName=tick.json");
    }
}
