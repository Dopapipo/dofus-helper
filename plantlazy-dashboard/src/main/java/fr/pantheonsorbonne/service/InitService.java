package fr.pantheonsorbonne.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import fr.pantheonsorbonne.service.StockService;
import fr.pantheonsorbonne.service.ServerTickService;
import fr.pantheonsorbonne.dto.InitMoneyDTO;

@ApplicationScoped
public class InitService {

    @Inject
    @RestClient
    StockService stockService;

    @Inject
    @RestClient
    ServerTickService serverTickService;

    @Inject
    @ConfigProperty(name = "money.amount")
    Double moneyAmount;

    @PostConstruct
    public void initialize() {
        try {

            // Appel à ms-plantlazy-stock pour initialiser MONEY
            InitMoneyDTO moneyDTO = new InitMoneyDTO(moneyAmount); // Montant initial par défaut
            stockService.initializeMoney(moneyDTO);

            // Appel à ms-plantlazy-server-ticks pour démarrer les ticks
            serverTickService.startTicks();

        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
