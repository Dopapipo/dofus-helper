package pantheonsorbonne.entity;

import java.io.Serializable;

public record TickMessage(TickType tickType, long timestamp) implements Serializable {

    @Override
    public String toString() {
        return "TickMessage{" +
                "tickType=" + tickType +
                ", timestamp=" + timestamp +
                '}';
    }
}

