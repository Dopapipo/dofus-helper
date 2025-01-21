package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class SunStat extends GenericPlantStat {
    public SunStat(int value) {
        super(value, 50, 2, StatType.SUN);
    }

    public SunStat() {
        super();
    }
}
