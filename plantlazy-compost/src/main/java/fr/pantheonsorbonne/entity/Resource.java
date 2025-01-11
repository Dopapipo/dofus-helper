package fr.pantheonsorbonne.entity;

import fr.pantheonsorbonne.exception.InsufficientResourceException;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResourceType type;

    private double quantity;
    private double dailyLimit;
    private double usedToday;
    private LocalDate lastResetDate;

    public Resource() {
        this.lastResetDate = LocalDate.now();
    }

    public boolean canExtract(double amount) {
        resetDailyUsageIfNeeded();
        if (type == ResourceType.FERTILIZER) {
            return quantity >= amount;
        }
        return quantity >= amount && (usedToday + amount) <= dailyLimit;
    }

    private void resetDailyUsageIfNeeded() {
        if (!lastResetDate.equals(LocalDate.now())) {
            usedToday = 0;
            lastResetDate = LocalDate.now();
        }
    }

    public void extract(double amount) {
        if (!canExtract(amount)) {
            throw new InsufficientResourceException(type);
        }
        quantity -= amount;
        if (type != ResourceType.FERTILIZER) {
            usedToday += amount;
        }
    }

    public void add(double amount) {
        quantity += amount;
    }

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUsedToday() {
        return usedToday;
    }

    public void setUsedToday(double usedToday) {
        this.usedToday = usedToday;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public LocalDate getLastResetDate() {
        return lastResetDate;
    }

    public void setLastResetDate(LocalDate lastResetDate) {
        this.lastResetDate = lastResetDate;
    }
}