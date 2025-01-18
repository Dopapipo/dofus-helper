package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.plant.stat.SoilStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.SunStat;
import fr.pantheonsorbonne.camel.processors.plant.stat.WaterStat;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Embeddable
@Table(name = "plants")
public class PlantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlantType type;

    @Column(name = "nutriment_conversion_rate", nullable = false)
    private double nutrimentConversionRate;

    @Embedded
    private WaterStat water;

    @Embedded
    private SunStat sun;

    @Embedded
    private SoilStat soil;

    @Column(name = "is_dead", nullable = false)
    private boolean isDead = false;

    @Column(name = "time_of_death")
    private Long timeOfDeath;

    @Column(name = "cause_of_death")
    private String causeOfDeath;

    protected PlantEntity() {
    }

    public PlantEntity(PlantType type, WaterStat water, SunStat sun, SoilStat soil) {
        this.type = type;
        this.water = water;
        this.sun = sun;
        this.soil = soil;
        this.nutrimentConversionRate = switch (type) {
            case FLOWER -> 0.70;
            case CACTUS -> 0.5;
            case TREE -> 0.25;
        };
    }


    public void markAsDead(String cause) {
        this.isDead = true;
        this.causeOfDeath = cause;
        this.timeOfDeath = System.currentTimeMillis();
    }
}
