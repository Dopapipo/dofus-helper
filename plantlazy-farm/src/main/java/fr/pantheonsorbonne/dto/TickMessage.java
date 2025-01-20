package fr.pantheonsorbonne.dto;

import java.io.Serializable;

public class TickMessage implements Serializable {
    private final TickType tickType;
    private final long timestamp;

    public TickMessage(TickType tickType, long timestamp) {
        this.tickType = tickType;
        this.timestamp = timestamp;
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

