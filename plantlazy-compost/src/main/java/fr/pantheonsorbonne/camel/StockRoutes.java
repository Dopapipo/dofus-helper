package fr.pantheonsorbonne.camel;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class StockRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:resourceUpdateRoute")
                .marshal().json()
                .to("sjms2:queue:log");
    }
}





