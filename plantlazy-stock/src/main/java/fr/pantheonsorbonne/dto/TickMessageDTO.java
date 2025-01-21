package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.enums.TickType;

import java.io.Serializable;

public record TickMessageDTO(TickType tickType, long timestamp) implements Serializable {

    public String getTickType() {
        return tickType.name();
    }
}