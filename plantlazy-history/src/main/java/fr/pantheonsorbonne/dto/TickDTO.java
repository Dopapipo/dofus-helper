package fr.pantheonsorbonne.dto;


public class TickDTO {
    private String type;

    public TickDTO() {
    }

    public TickDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return "TickDTO{" +
                "type='" + type + '\'' +
                '}';
    }
}
