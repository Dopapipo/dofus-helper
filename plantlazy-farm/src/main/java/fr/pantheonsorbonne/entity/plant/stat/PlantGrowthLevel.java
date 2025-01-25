package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class PlantGrowthLevel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    int value;
    int MAX_VALUE = 10;


    public PlantGrowthLevel(int value) {
        this.value = value;
    }

    public boolean isMature() {
        return this.getGrowthPercentage()>0.7;
    }

    private double getGrowthPercentage() {
        return (double) value / MAX_VALUE;
    }
    public PlantGrowthLevel() {

    }

    public void grow() {
        if (value < MAX_VALUE) {
            value++;
        }
    }
}
