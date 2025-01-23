package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.plant.PlantType;
import fr.pantheonsorbonne.entity.plant.stat.*;

import java.util.UUID;

public record LogMessageUpdate(LogType type, UUID id, PlantType plantType, PlantGrowthLevel growthLevel, WaterStat water, SunStat sun, SoilStat soil) {

}


 