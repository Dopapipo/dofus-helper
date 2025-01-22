package fr.pantheonsorbonne.dto.log;

public class PlantDeadLogDTO extends LogDTO{

    private String plantId;

    public PlantDeadLogDTO() {
    }



    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
}
