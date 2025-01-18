package fr.pantheonsorbonne.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class SeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Exemple : "Tomate", "Courgette", "Concombre"
    private double price; // Prix unitaire de la graine
    private int quantity; // Quantité disponible pour l'offre quotidienne

    public SeedEntity() {
    }

    public SeedEntity(String type, double price, int quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SeedEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
