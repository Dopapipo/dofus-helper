package fr.pantheonsorbonne.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantDTO {
    private String id;
    private String type;
    private int water; // Contiendra water.value
    private int sun;   // Contiendra sun.value
    private int soil;  // Contiendra soil.value


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWater() {
        return water;
    }

    // Setter personnalisé pour mapper directement water.value
    @JsonSetter("water")
    public void setWater(Object water) {
        if (water instanceof java.util.Map) {
            Object value = ((java.util.Map<?, ?>) water).get("value");
            if (value instanceof Integer) {
                this.water = (Integer) value;
            }
        }
    }

    public int getSun() {
        return sun;
    }

    // Setter personnalisé pour mapper directement sun.value
    @JsonSetter("sun")
    public void setSun(Object sun) {
        if (sun instanceof java.util.Map) {
            Object value = ((java.util.Map<?, ?>) sun).get("value");
            if (value instanceof Integer) {
                this.sun = (Integer) value;
            }
        }
    }

    public int getSoil() {
        return soil;
    }

    // Setter personnalisé pour mapper directement soil.value
    @JsonSetter("soil")
    public void setSoil(Object soil) {
        if (soil instanceof java.util.Map) {
            Object value = ((java.util.Map<?, ?>) soil).get("value");
            if (value instanceof Integer) {
                this.soil = (Integer) value;
            }
        }
    }

    @Override
    public String toString() {
        return "PlantDTO{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", water=" + water +
                ", sun=" + sun +
                ", soil=" + soil +
                '}';
    }
}
