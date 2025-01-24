package fr.pantheonsorbonne.dto.log;

import java.util.UUID;

public class PlantSoldLogDTO extends LogDTO{

    private UUID id;

    public PlantSoldLogDTO() {
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
