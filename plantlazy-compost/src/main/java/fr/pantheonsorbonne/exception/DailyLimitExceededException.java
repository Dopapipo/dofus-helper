package fr.pantheonsorbonne.exception;

public class DailyLimitExceededException extends ResourceException {
    public DailyLimitExceededException(String message) {
        super(ResourceExceptionCode.DAILY_LIMIT_EXCEEDED, message);
    }
}