package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.PlantGrowthLevel;
import fr.pantheonsorbonne.entity.plant.stat.SoilStat;
import fr.pantheonsorbonne.entity.plant.stat.SunStat;
import fr.pantheonsorbonne.entity.plant.stat.WaterStat;

import java.util.UUID;

public record LogMessagePlantCreatedOrUpdated(LogType type, UUID id, PlantType plantType, PlantGrowthLevel growthLevel,
                                              WaterStat water, SunStat sun, SoilStat soil) {
}
