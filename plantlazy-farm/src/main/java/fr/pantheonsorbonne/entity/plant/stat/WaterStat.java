package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class WaterStat extends GenericPlantStat {
    public WaterStat(int value) {
        super(value, 60, 3, StatType.WATER);
    }

    public WaterStat() {
        super();
    }
}
