package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import jakarta.persistence.*;

@Entity
public class SeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlantType type;

    @Enumerated(EnumType.STRING)
    private SeedQuality quality;

    private double price;

    public SeedEntity() {
    }

    public SeedEntity(PlantType type, double price, SeedQuality quality) {
        this.type = type;
        this.price = price;
        this.quality = quality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SeedQuality getQuality() {
        return quality;
    }

    public void setQuality(SeedQuality quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "SeedEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
