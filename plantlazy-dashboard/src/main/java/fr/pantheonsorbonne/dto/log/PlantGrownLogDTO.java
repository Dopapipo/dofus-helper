package fr.pantheonsorbonne.dto.log;

public class PlantGrownLogDTO {

    private String type;  // "PLANT_GROWN"
    private String plantId;

    public PlantGrownLogDTO() {
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
