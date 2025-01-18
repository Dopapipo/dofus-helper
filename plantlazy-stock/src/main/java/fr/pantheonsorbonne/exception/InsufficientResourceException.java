package fr.pantheonsorbonne.exception;

public class InsufficientResourceException extends ResourceException {
    public InsufficientResourceException(String message) {
        super(ResourceExceptionCode.INSUFFICIENT_RESOURCE, message);
    }
}