package fr.pantheonsorbonne.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "dead_plants")
public class DeadPlant {
    @Id
    private String id;

    private String type;
    private int size;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "processed")
    private boolean processed;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LocalDateTime getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDateTime deathDate) {
        this.deathDate = deathDate;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}