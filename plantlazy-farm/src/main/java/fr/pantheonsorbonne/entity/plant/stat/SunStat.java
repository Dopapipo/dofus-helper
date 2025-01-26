package fr.pantheonsorbonne.entity.plant.stat;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class SunStat extends GenericPlantStat implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public SunStat(int value) {
        super(value, 70, 1, StatType.SUN);
    }

    public SunStat() {
        super();
    }

}
