package fr.pantheonsorbonne.service;

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

    @Inject
    @RestClient
    ServerTickService serverTickService;

    public void initializeSimulation(InitMoneyDTO initMoneyDTO) {
        this.initMoney(initMoneyDTO);
        serverTickService.startTicks();
    }

    private void initMoney(InitMoneyDTO initMoneyDTO) {
        System.out.println("Sending to initEndpoint:" + initMoneyDTO);
        producerTemplate.sendBody(initEndpoint, initMoneyDTO);

    }
}
