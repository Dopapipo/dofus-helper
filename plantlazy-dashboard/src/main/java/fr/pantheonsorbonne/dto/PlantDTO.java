package fr.pantheonsorbonne.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlantDTO {
    private UUID id;
    private String type;
    private int water;
    private int sun;
    private int soil;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public void setWater(int water) {
        this.water = water;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public int getSoil() {
        return soil;
    }

    public void setSoil(int soil) {
        this.soil = soil;
    }

    @Override
    public String toString() {
        return "PlantDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", water=" + water +
                ", sun=" + sun +
                ", soil=" + soil +
                '}';
    }
}
