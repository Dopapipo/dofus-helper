package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.exception.ResourceException;

import java.time.Instant;

public record ErrorResponse(String code, String message, String timestamp) {

    public ErrorResponse(ResourceException ex) {
        this(ex.getCode().getCode(), ex.getMessage(), Instant.now().toString());
    }
}