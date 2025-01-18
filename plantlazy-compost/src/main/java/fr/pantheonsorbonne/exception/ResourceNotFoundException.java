package fr.pantheonsorbonne.exception;

public class ResourceNotFoundException extends ResourceException {
    public ResourceNotFoundException(String message) {
        super(ResourceExceptionCode.RESOURCE_NOT_FOUND, message);
    }
}