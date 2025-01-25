package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class SoilStat extends GenericPlantStat implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public SoilStat(int value) {
        super(value, 70, 80, StatType.SOIL);
    }

    public SoilStat() {
        super();
    }
}
