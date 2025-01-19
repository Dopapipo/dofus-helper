package fr.pantheonsorbonne.entity;

import java.io.Serializable;

public class TickMessage implements Serializable {

    private TickType tickType;
    private long timestamp;

    public TickMessage() {
    }

    public TickMessage(TickType tickType, long timestamp) {
        this.tickType = tickType;
        this.timestamp = timestamp;
    }

    // Getters et Setters
    public TickType getTickType() {
        return tickType;
    }

    public void setTickType(TickType tickType) {
        this.tickType = tickType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TickMessage{" +
                "tickType=" + tickType +
                ", timestamp=" + timestamp +
                '}';
    }
}
