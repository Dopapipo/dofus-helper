package fr.pantheonsorbonne.exception;

public class InvalidQuantityException extends ResourceException {
    public InvalidQuantityException(String message) {
        super(ResourceExceptionCode.INVALID_QUANTITY, message);
    }
}