package fr.pantheonsorbonne.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.InitMoneyDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class InitService {
    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    @ConfigProperty(name = "init.endpoint")
    String initEndpoint;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    @RestClient
    ServerTickService serverTickService;

    public void initializeSimulation(InitMoneyDTO initMoneyDTO) {
        this.initMoney(initMoneyDTO);
        serverTickService.startTicks();
    }

    private void initMoney(InitMoneyDTO initMoneyDTO) {
        try {
            String json = objectMapper.writeValueAsString(initMoneyDTO);
            System.out.println("Sending JSON to initEndpoint: " + json);
            producerTemplate.sendBodyAndHeader(initEndpoint, json, "Content-Type", "application/json");
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize InitMoneyDTO", e);
        }
    }
}
