package fr.pantheonsorbonne.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fertilizers")
public class Fertilizer {
    @Id
    private String id;

    private int quantity;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @OneToOne
    @JoinColumn(name = "dead_plant_id")
    private DeadPlant deadPlant;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public DeadPlant getDeadPlant() {
        return deadPlant;
    }

    public void setDeadPlant(DeadPlant deadPlant) {
        this.deadPlant = deadPlant;
    }
}