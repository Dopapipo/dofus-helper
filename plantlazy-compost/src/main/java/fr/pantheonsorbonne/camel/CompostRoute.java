package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.dto.FertilizerDTO;
import fr.pantheonsorbonne.entity.DeadPlant;
import fr.pantheonsorbonne.service.CompostService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

@ApplicationScoped
public class CompostRoute extends RouteBuilder {

    @Inject
    CompostService compostService;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .bindingMode(RestBindingMode.json)
                .component("")
                .contextPath("")
                .port("");

        from("artemis:dead-plants-queue")
                .unmarshal().json(DeadPlant.class)
                .bean(compostService, "compostPlant")
                .marshal().json()
                .to("artemis:farm-fertilizer-queue");

        rest("/compost")
                .post("/process")
                .type(DeadPlant.class)
                .outType(FertilizerDTO.class)
                .to("direct:processCompost");

        from("direct:processCompost")
                .bean(compostService, "compostPlant");
    }
}