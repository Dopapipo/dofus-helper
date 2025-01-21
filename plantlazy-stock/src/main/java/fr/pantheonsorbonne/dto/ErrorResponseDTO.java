package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.exception.ResourceException;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public record ErrorResponseDTO(String code, String message, String timestamp) {

    public ErrorResponseDTO(ResourceException ex) {
        this(
                ex.getCode().getCode(),
                ex.getMessage(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
                        .withZone(ZoneId.of("Europe/Paris"))
                        .format(Instant.now())
        );
    }

}