package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class WaterStat extends GenericPlantStat {
    private int value;

    public WaterStat(int value) {
        super(value, 65, 2, StatType.WATER);
        this.value = value;
    }

    public WaterStat() {
        super();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
