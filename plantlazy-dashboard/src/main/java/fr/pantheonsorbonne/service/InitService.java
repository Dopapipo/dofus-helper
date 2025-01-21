package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.InitMoneyDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class InitService {

    @Inject
    @RestClient
    StockService stockService;

    @Inject
    @RestClient
    ServerTickService serverTickService;

    public void initializeSimulation(double money) {
        InitMoneyDTO initMoneyDTO = new InitMoneyDTO(money);
        stockService.initializeMoney(initMoneyDTO);

        serverTickService.startTicks();
    }
}