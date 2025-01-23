package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.plant.stat.PlantGrowthLevel;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.StatType;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;
import jakarta.persistence.*;

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
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "soil_value"))
    })
    protected SoilStat soil;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "water_value"))
    })
    protected WaterStat water;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "sun_value"))
    })
    protected SunStat sun;


    public PlantGrowthLevel getGrowthLevel() {
        return growthLevel;
    }

    public void setGrowthLevel(PlantGrowthLevel growthLevel) {
        this.growthLevel = growthLevel;
    }

    @Embedded
    protected PlantGrowthLevel growthLevel = new PlantGrowthLevel();

    public List<PlantStat> getStats() {
        return List.of(water, sun, soil);
    }

    @Column(name = "is_dead", nullable = false)
    private boolean isDead = false;

    @Column(name = "time_of_death")
    private Long timeOfDeath;

    @Column(name = "cause_of_death")
    private String causeOfDeath;
    @Column(name = "composted")
    private Boolean composted = false;


    @Column(name = "sold")
    private boolean sold = false;

    public PlantEntity(PlantDTO plantDTO) {
        this.id = plantDTO.id();
        this.type = plantDTO.type();
        this.water = plantDTO.water();
        this.sun = plantDTO.sun();
        this.soil = plantDTO.soil();
        this.growthLevel = plantDTO.growthLevel();
        this.isDead = plantDTO.isDead();
        this.timeOfDeath = plantDTO.timeOfDeath();
        this.causeOfDeath = plantDTO.causeOfDeath();
        this.sold = plantDTO.sold();
        this.composted = plantDTO.composted();
    }

    public boolean isSold() {
        return this.sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Boolean getComposted() {
        return composted;
    }

    public void setComposted(Boolean composted) {
        this.composted = composted;
    }

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

    public PlantEntity(UUID id, PlantType type, WaterStat water, SunStat sun, SoilStat soil, PlantGrowthLevel growthLevel,
                       boolean isDead, Long timeOfDeath, String causeOfDeath, Integer soldAtDay, Boolean composted) {
        this.id = id;
        this.type = type;
        this.water = water;
        this.sun = sun;
        this.soil = soil;
        this.growthLevel = growthLevel;
        this.isDead = isDead;
        this.timeOfDeath = timeOfDeath;
        this.causeOfDeath = causeOfDeath;
        this.sold = soldAtDay != null;
        this.composted = composted;
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
        this.growthLevel.grow();
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

    public boolean isMature() {
        return this.growthLevel.isMature();
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
