package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

@Embeddable
public class SoilStat extends GenericPlantStat {
    private int value;

    public SoilStat(int value) {
        super(value, 70, 1, StatType.SOIL);
        this.value = value;
    }

    public SoilStat() {
        super();
    }
}
