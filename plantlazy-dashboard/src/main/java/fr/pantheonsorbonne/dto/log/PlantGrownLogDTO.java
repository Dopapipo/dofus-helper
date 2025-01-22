package fr.pantheonsorbonne.dto.log;

public class PlantGrownLogDTO extends LogDTO{

    private String plantId;

    public PlantGrownLogDTO() {
    }


    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
}
