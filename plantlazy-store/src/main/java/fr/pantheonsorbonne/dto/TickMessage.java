package fr.pantheonsorbonne.dto;

import java.io.Serializable;

public class TickMessage implements Serializable {
    private TickType tickType;
    private long timestamp;

    public TickMessage(TickType tickType, long timestamp) {
        this.tickType = tickType;
        this.timestamp = timestamp;
    }

    public void setTickType(TickType tickType) {
        this.tickType = tickType;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public TickMessage() {
    }

    public TickType getTickType() {
        return tickType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "TickMessage{" +
                "tickType=" + tickType +
                ", timestamp=" + timestamp +
                '}';
    }
}

