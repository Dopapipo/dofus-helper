package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.dto.PlantStats;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PlantEntity {

    private Double height;
    @Convert
    private PlantStats stats;
    @Id
    @GeneratedValue
    private Long id;

    public PlantEntity(Double height, PlantStats stats) {
        this.height = height;
        this.stats = stats;
    }

    public PlantEntity() {
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public PlantStats getStats() {
        return stats;
    }

    public void setStats(PlantStats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "PlantEntity{" +
                "height=" + height +
                ", stats=" + stats.toString() +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
