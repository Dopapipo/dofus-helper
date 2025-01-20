package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import jakarta.persistence.*;

@Entity
public class SeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Stocker l'énumération sous forme de chaîne
    private PlantType type; // Remplace le champ String par l'énumération PlantType

    private double price; // Prix unitaire de la graine
    private int availableQuantity; // Quantité disponible pour l'offre quotidienne

    public SeedEntity() {
    }

    public SeedEntity(PlantType type, double price, int quantity) {
        this.type = type;
        this.price = price;
        this.availableQuantity = availableQuantity;
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

    public int getQuantity() {
        return availableQuantity;
    }

    public void setQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

/*    public SeedQuality getSeedType() {
        return seedType;
    }

    public void setSeedType(SeedQuality seedType) {
        this.seedType = seedType;
    }*/

    @Override
    public String toString() {
        return "SeedEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + availableQuantity +
                '}';
    }



}
