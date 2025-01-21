package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.PlantGrowthLevel;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;
import java.util.UUID;

public class PlantDTO {
    private UUID id;

    public PlantGrowthLevel getGrowthLevel() {
        return growthLevel;
    }

    public void setGrowthLevel(PlantGrowthLevel growthLevel) {
        this.growthLevel = growthLevel;
    }

    private PlantType type;
    private WaterStat water;
    private SunStat sun;
    private SoilStat soil;
    private boolean isDead;
    private Long timeOfDeath;
    private String causeOfDeath;
    private PlantGrowthLevel growthLevel;

    public int getSoldAtDay() {
        return soldAtDay;
    }

    public void setSoldAtDay(int soldAtDay) {
        this.soldAtDay = soldAtDay;
    }

    public boolean isComposted() {
        return composted;
    }

    public void setComposted(boolean composted) {
        this.composted = composted;
    }

    private int soldAtDay;
    private boolean composted;

    public PlantDTO() {
    }

    public PlantDTO(UUID id, PlantType type, WaterStat water, SunStat sun, SoilStat soil, boolean isDead, Long timeOfDeath, String causeOfDeath, PlantGrowthLevel growthLevel, int soldAtDay, boolean composted) {
        this.id = id;
        this.type = type;
        this.water = water;
        this.sun = sun;
        this.soil = soil;
        this.isDead = isDead;
        this.timeOfDeath = timeOfDeath;
        this.causeOfDeath = causeOfDeath;
        this.growthLevel=growthLevel;
        this.soldAtDay = soldAtDay;
        this.composted = composted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
    }

    public WaterStat getWater() {
        return water;
    }

    public void setWater(WaterStat water) {
        this.water = water;
    }

    public SunStat getSun() {
        return sun;
    }

    public void setSun(SunStat sun) {
        this.sun = sun;
    }

    public SoilStat getSoil() {
        return soil;
    }

    public void setSoil(SoilStat soil) {
        this.soil = soil;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Long getTimeOfDeath() {
        return timeOfDeath;
    }

    public void setTimeOfDeath(Long timeOfDeath) {
        this.timeOfDeath = timeOfDeath;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }
}
