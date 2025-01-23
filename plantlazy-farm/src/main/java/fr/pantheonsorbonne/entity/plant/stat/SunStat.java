package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class SunStat extends GenericPlantStat {
    private int value;

    public SunStat(int value) {
        super(value, 50, 2, StatType.SUN);
        this.value = value;
    }

    public SunStat() {
        super();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
