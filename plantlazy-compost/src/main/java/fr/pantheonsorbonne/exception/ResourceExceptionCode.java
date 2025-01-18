package fr.pantheonsorbonne.exception;

public enum ResourceExceptionCode {
    INSUFFICIENT_RESOURCE("RST-001"),
    DAILY_LIMIT_EXCEEDED("RST-002"),
    RESOURCE_NOT_FOUND("RST-003"),
    INVALID_QUANTITY("RST-004"),
    DATABASE_ERROR("RST-005"),
    MESSAGE_PROCESSING_ERROR("RST-006");

    private final String code;

    ResourceExceptionCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}