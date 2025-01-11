package fr.pantheonsorbonne.entity;
import fr.pantheonsorbonne.camel.processors.plant.PlantType;
import fr.pantheonsorbonne.camel.processors.plant.stat.FullPlantStats;
import fr.pantheonsorbonne.camel.processors.seeds.SeedQuality;
import jakarta.persistence.*;
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

    @Embedded
    private FullPlantStats initialStats;

    protected SeedEntity() {
        // Default constructor for JPA
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
