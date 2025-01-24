package fr.pantheonsorbonne.dto.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.pantheonsorbonne.dto.PlantDTO;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantUpdateLogDTO extends LogDTO {

    private final PlantDTO plant; // Objet encapsulant les détails de la plante

    @JsonCreator
    public PlantUpdateLogDTO(
            @JsonProperty("id") String id,
            @JsonProperty("plantType") String plantType,
            @JsonProperty("water") int water,
            @JsonProperty("sun") int sun,
            @JsonProperty("soil") int soil
    ) {
        this.plant = new PlantDTO();
        this.plant.setId(UUID.fromString(id));
        this.plant.setType(plantType);
        this.plant.setWater(water);
        this.plant.setSun(sun);
        this.plant.setSoil(soil);
    }

    public PlantDTO getPlant() {
        return plant;
    }

    @Override
    public String toString() {
        return "PlantUpdateLogDTO{" +
                "type=" + getType() +
                ", plant=" + plant +
                '}';
    }
}
