package fr.pantheonsorbonne.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.InitMoneyDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class InitService {
    @Inject
    JMSContext context;

    @Inject
    @ConfigProperty(name = "init.endpoint")
    String initEndpoint;


    @Inject
    @RestClient
    ServerTickService serverTickService;

    public void initializeSimulation(InitMoneyDTO initMoneyDTO) {
        this.initMoney(initMoneyDTO);
        serverTickService.startTicks();
    }

    private void initMoney(InitMoneyDTO initMoneyDTO) {
        try {
            context.createProducer().send(context.createQueue(initEndpoint), context.createTextMessage(new ObjectMapper().writeValueAsString(initMoneyDTO)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize InitMoneyDTO", e);
        }
    }
}
