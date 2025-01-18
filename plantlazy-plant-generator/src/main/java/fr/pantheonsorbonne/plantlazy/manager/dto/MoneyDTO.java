
package fr.pantheonsorbonne.plantlazy.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoneyDTO {
    @JsonProperty("amount")
    private double amount;

    public MoneyDTO() {}

    public MoneyDTO(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
