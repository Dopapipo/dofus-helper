package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.plant.stat.StatType;

public class ResourceRequest {
    private ResourceType type;
    private int quantity;
    private OperationTag operationTag;

    public ResourceRequest(ResourceType type, int quantity, OperationTag operationTag) {
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

    public OperationTag getOperationTag() {
        return operationTag;
    }

    public void setOperationTag(OperationTag operationTag) {
        this.operationTag = operationTag;
    }
}
