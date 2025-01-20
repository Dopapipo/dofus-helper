package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.plant.stat.StatType;

public class ResourceRequest {
    private StatType type;
    private int quantity;
    private String operationTag;

    public ResourceRequest(StatType type, int quantity, String operationTag) {
        this.type = type;
        this.quantity = quantity;
        this.operationTag = operationTag;
    }

    public StatType getType() {
        return type;
    }

    public void setType(StatType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOperationTag() {
        return operationTag;
    }

    public void setOperationTag(String operationTag) {
        this.operationTag = operationTag;
    }
}
