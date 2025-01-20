package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.entity.seed.SeedQuality;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "seeds")
public class SeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlantType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeedQuality quality;

    private FullPlantStats initialStats;

    protected SeedEntity() {
    }

    public SeedEntity(PlantType type, SeedQuality quality, FullPlantStats initialStats) {
        this.type = type;
        this.quality = quality;
        this.initialStats = initialStats;
    }

    public PlantType getType() {
        return type;
    }

    public SeedQuality getQuality() {
        return quality;
    }

    public FullPlantStats getInitialStats() {
        return initialStats;
    }

}
