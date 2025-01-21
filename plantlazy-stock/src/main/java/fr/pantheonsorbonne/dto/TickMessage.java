package fr.pantheonsorbonne.dto;

import java.io.Serializable;

public record TickMessage(TickType tickType, long timestamp) implements Serializable {

    public String getTickType() {
        return tickType.name();
    }
}