
package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.MoneyDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MoneyService {

    public void processMoney(MoneyDTO money) {
        System.out.println("Processing money transaction: Amount " + money.getAmount());
        // Business logic to handle money transactions
        // Example: Update user balance in the system
    }
}
