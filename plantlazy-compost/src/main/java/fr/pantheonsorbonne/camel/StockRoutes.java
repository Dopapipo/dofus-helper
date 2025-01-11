package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.dto.ResourceRequest;
import fr.pantheonsorbonne.exception.InsufficientResourceException;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

@ApplicationScoped
public class StockRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {



    }
}

