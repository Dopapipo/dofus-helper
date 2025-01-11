package fr.pantheonsorbonne.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.pantheonsorbonne.entity.ResourceType;

public class ResourceRequest {

    private ResourceType type;
    private double amount;

    public ResourceRequest(@JsonProperty("type") ResourceType type, @JsonProperty("amount") double amount) {
        this.type = type;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public ResourceType getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

}