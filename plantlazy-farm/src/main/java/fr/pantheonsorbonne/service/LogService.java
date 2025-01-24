package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.LogMessagePlantCreatedOrUpdated;
import fr.pantheonsorbonne.dto.LogMessagePlantDiedOrSold;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class LogService {
    @Inject
    ProducerTemplate producerTemplate;

    public void sendLogPlantCreatedOrUpdated(LogMessagePlantCreatedOrUpdated message) {
        producerTemplate.sendBody("direct:logQueue", message);
    }

    public void sendLogPlantDiedOrSold(LogMessagePlantDiedOrSold message) {
        producerTemplate.sendBody("direct:logQueue", message);
    }

}
