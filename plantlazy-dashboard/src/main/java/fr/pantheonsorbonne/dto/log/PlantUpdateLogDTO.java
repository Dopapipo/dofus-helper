package fr.pantheonsorbonne.dto.log;

import fr.pantheonsorbonne.dto.PlantDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantUpdateLogDTO extends LogDTO {

    private PlantDTO plantDTO;

    public PlantDTO getPlantDTO() {
        return plantDTO;
    }

    public void setPlantDTO(PlantDTO plant) {
        this.plantDTO = plant;
    }
}
