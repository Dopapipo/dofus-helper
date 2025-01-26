package fr.pantheonsorbonne.dto.log;

public abstract class LogDTO  {
    LogType type;

    public LogDTO() {
    }

    public LogType getType() {
        return type;
    }
    public void setType(LogType type) {
        this.type = type;
    }
}
