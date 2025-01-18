
package fr.pantheonsorbonne.plantlazy.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeadPlantDTO {
    @JsonProperty("plantId")
    private String plantId;

    public DeadPlantDTO() {}

    public DeadPlantDTO(String plantId) {
        this.plantId = plantId;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
}
