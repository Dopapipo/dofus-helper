package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.StatType;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "plants")
public class PlantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlantType type;
    @Embedded
    protected WaterStat water;

    @Embedded
    protected SunStat sun;

    @Embedded
    protected SoilStat soil;


    public List<PlantStat> getStats() {
        return List.of(water, sun, soil);
    }

    @Column(name = "is_dead", nullable = false)
    private boolean isDead = false;

    @Column(name = "time_of_death")
    private Long timeOfDeath;

    @Column(name = "cause_of_death")
    private String causeOfDeath;

    public Boolean getComposted() {
        return composted;
    }

    public void setComposted(Boolean composted) {
        this.composted = composted;
    }

    @Column(name = "composted")
    private Boolean composted = false;

    protected PlantEntity() {
    }

    public PlantEntity(PlantType type, FullPlantStats stats) {
        this(type, stats.getWaterStat(), stats.getSunStat(), stats.getSoilStat());
    }

    public UUID getId() {
        return this.id;
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

    public PlantEntity(PlantType type, WaterStat water, SunStat sun, SoilStat soil) {
        this.type = type;
        this.water = water;
        this.sun = sun;
        this.soil = soil;
    }

    public void markAsDead(String cause) {
        this.isDead = true;
        this.causeOfDeath = cause;
        this.timeOfDeath = System.currentTimeMillis();
    }

    public boolean isDead() {
        return (this.soil.isDead() || this.water.isDead() || this.sun.isDead()) || (!this.soil.isHealthy() && !this.water.isHealthy() && !this.sun.isHealthy());
    }

    public void grow() {
        if (isDead) {
            return;
        }
        this.water.tick();
        this.sun.tick();
        this.soil.tick();
        if (this.isDead()) {
            this.markAsDead("Lack of ressources");
        }
    }

    public int getRemainingTicksOfHealthyFor(PlantStat stat) {
        return stat.getRemainingTicksOfHealthy();
    }


    public void feed(StatType type, int quantity) {
        PlantStat stat = getStatToFeed(type);
        stat.feed(quantity);
    }

    private PlantStat getStatToFeed(StatType type) {
        return switch (type) {
            case WATER -> this.water;
            case SOIL -> this.soil;
            case SUN -> this.sun;
        };
    }

    @Override
    public String toString() {
        return "PlantEntity{" +
                "id=" + id +
                ", type=" + type +
                ", water=" + water.toString() +
                ", sun=" + sun.toString() +
                ", soil=" + soil.toString() +
                ", isDead=" + isDead +
                ", timeOfDeath=" + timeOfDeath +
                ", causeOfDeath='" + causeOfDeath + '\'' +
                '}';
    }
}
