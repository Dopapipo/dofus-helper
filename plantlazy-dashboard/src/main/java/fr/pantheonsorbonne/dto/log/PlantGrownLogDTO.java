package fr.pantheonsorbonne.dto.log;

import java.util.UUID;

public class PlantGrownLogDTO extends LogDTO{

    private UUID plantId;

    public PlantGrownLogDTO() {
    }


    public UUID getPlantId() {
        return plantId;
    }

    public void setPlantId(UUID plantId) {
        this.plantId = plantId;
    }
}
