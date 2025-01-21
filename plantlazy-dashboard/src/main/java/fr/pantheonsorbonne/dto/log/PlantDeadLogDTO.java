package fr.pantheonsorbonne.dto.log;

public class PlantDeadLogDTO {

    private String type;
    private String plantId;

    public PlantDeadLogDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
}
