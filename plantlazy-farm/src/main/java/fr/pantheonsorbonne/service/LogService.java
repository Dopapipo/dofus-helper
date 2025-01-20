package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.LogMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class LogService {
    @Inject
    ProducerTemplate producerTemplate;

    public void sendLog(LogMessage message) {
        producerTemplate.sendBody("direct:logQueue", message);
    }
}
