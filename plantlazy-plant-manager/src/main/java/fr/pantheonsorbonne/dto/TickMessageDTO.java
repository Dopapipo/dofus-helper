package fr.pantheonsorbonne.dto;

import java.io.Serializable;

public record TickMessageDTO(TickType tickType, long timestamp) implements Serializable {

    @Override
    public String toString() {
        return "TickMessage{" +
                "tickType=" + tickType +
                ", timestamp=" + timestamp +
                '}';
    }
}