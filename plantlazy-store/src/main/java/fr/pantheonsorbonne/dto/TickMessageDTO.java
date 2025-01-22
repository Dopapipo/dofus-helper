package fr.pantheonsorbonne.dto;

public class TickMessageDTO {
    private TickTypeDTO tickType;
    private long timestamp;

    // Getters et setters
    public TickTypeDTO getTickType() {
        return tickType;
    }

    public void setTickType(TickTypeDTO tickType) {
        this.tickType = tickType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

