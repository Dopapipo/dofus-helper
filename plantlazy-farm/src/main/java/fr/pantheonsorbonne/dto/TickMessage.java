package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.TickType;

import java.io.Serializable;

public record TickMessage(TickType tickType, long timestamp) implements Serializable {

    public TickType getTickType() {
        return this.tickType;
    }

}
