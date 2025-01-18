package fr.pantheonsorbonne.dto;

public class LogMessage {
    private String type; // Type de message, ex. RESOURCE_UPDATE, PLANT_UPDATE
    private Object payload; // Données spécifiques au type de message

    public LogMessage() {}

    public LogMessage(String type, Object payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "type='" + type + '\'' +
                ", payload=" + payload +
                '}';
    }
}
