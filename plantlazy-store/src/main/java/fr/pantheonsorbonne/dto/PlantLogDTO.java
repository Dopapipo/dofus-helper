package fr.pantheonsorbonne.dto;

public class PlantLogDTO {
    private Long id;
    private String type;
    private double price;
    private LogType logType;

    public PlantLogDTO(Long id, String type, double price, LogType logType) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.logType = logType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    @Override
    public String toString() {
        return "PlantLogDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", logType=" + logType +
                '}';
    }
}
