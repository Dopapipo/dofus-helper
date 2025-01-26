package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.entity.enums.PlantType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class PlantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PlantType type;

    private double price;

    public PlantEntity() {
    }

    public PlantEntity(PlantType type, double price) {
        this.type = type;
        this.price = price;
    }

    public UUID getId() {
        return id;
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

}
