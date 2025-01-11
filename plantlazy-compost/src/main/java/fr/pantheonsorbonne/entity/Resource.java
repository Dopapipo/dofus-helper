package fr.pantheonsorbonne.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResourceType type;

    private Double quantity;
    private Double dailyLimit;
    private OffsetDateTime lastRefreshDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }


    public OffsetDateTime getLastRefreshDate() {
        return lastRefreshDate;
    }

    public void setLastRefreshDate(LocalDateTime now) {
        this.lastRefreshDate = OffsetDateTime.of(now, OffsetDateTime.now().getOffset());
    }
}