package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.LogMessagePlantDied;
import fr.pantheonsorbonne.dto.LogMessagePlantSold;
import fr.pantheonsorbonne.dto.LogMessageUpdate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class LogService {
    @Inject
    ProducerTemplate producerTemplate;

    public void sendLogUpdate(LogMessageUpdate message) {
        producerTemplate.sendBody("direct:logQueue", message);
    }

    public void sendLogPlantDied(LogMessagePlantDied message) {
        producerTemplate.sendBody("direct:logQueue", message);
    }

    public void sendLogPlantSold(LogMessagePlantSold message) {
        producerTemplate.sendBody("direct:logQueue", message);
    }

}
