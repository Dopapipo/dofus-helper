package fr.pantheonsorbonne.exception;


public abstract class
ResourceException extends RuntimeException {
    private final ResourceExceptionCode code;

    protected ResourceException(ResourceExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

    public ResourceExceptionCode getCode() {
        return code;
    }
}