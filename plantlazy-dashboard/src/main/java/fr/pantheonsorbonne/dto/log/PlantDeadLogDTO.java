package fr.pantheonsorbonne.dto.log;

import java.util.UUID;

public class PlantDeadLogDTO extends LogDTO{

    private UUID id;

    public PlantDeadLogDTO() {
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
