package fr.pantheonsorbonne.exception;

public class DatabaseException extends ResourceException {
    private final Throwable cause;

    public DatabaseException(String message, Throwable cause) {
        super(ResourceExceptionCode.DATABASE_ERROR, message);
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}