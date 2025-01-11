package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.ResourceType;

public class ResourceResponse {

    private ResourceType type;
    private double amount;
    private String providerId;

    public ResourceResponse(ResourceType type, double amount, String providerId) {
        this.type = type;
        this.amount = amount;
        this.providerId = providerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getProviderId() {
        return providerId;
    }

    public ResourceType getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

}