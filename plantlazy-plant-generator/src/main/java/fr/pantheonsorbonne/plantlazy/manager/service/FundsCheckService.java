
package fr.pantheonsorbonne.plantlazy.manager.service;

import org.apache.camel.ProducerTemplate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FundsCheckService {

    @Inject
    private ProducerTemplate producerTemplate;

    public Boolean checkFunds(String accountId, Double amount) {
        String endpointUri = "direct:check-funds"; // Camel route for checking funds
        try {
            return (Boolean) producerTemplate.requestBody(endpointUri, new FundsRequest(accountId, amount));
        } catch (Exception e) {
            System.err.println("Error checking funds: " + e.getMessage());
            return false;
        }
    }

    public static class FundsRequest {
        private String accountId;
        private Double amount;

        public FundsRequest(String accountId, Double amount) {
            this.accountId = accountId;
            this.amount = amount;
        }

        public String getAccountId() {
            return accountId;
        }

        public Double getAmount() {
            return amount;
        }
    }
}
