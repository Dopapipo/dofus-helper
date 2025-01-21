package fr.pantheonsorbonne.mapper;

import fr.pantheonsorbonne.dto.LogMessage;
import fr.pantheonsorbonne.dto.LogType;
import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantGrowthLevel;

public class PlantMapper {

    public static LogMessage toPlantDiedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessage(LogType.PLANT_DEAD, plantDTO);
    }

    public static LogMessage toPlantCreatedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessage(LogType.PLANT_CREATED, plantDTO);
    }

    public static LogMessage toPlantUpdatedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessage(LogType.PLANT_UPDATE, plantDTO);
    }

    public static PlantDTO toPlantDTO(PlantEntity plant) {
        return new PlantDTO(
                plant.getId(),
                plant.getType(),
                plant.getWater(),
                plant.getSun(),
                plant.getSoil(),
                plant.isDead(),
                plant.getTimeOfDeath(),
                plant.getCauseOfDeath(),
                plant.getGrowthLevel(),
                plant.getSoldAtDay(),
                plant.getComposted()
        );
    }
    public static PlantEntity toPlantEntity(PlantDTO plantDTO) {
        PlantEntity plantEntity = new PlantEntity(
                plantDTO.getId(),
                plantDTO.getType(),
                plantDTO.getWater(),
                plantDTO.getSun(),
                plantDTO.getSoil(),
                plantDTO.getGrowthLevel(),
                plantDTO.isDead(),
                plantDTO.getTimeOfDeath(),
                plantDTO.getCauseOfDeath(),
                plantDTO.getSoldAtDay(),
                plantDTO.isComposted()
        );
        plantEntity.setId(plantDTO.getId());
        plantEntity.setDead(plantDTO.isDead());
        plantEntity.setTimeOfDeath(plantDTO.getTimeOfDeath());
        plantEntity.setCauseOfDeath(plantDTO.getCauseOfDeath());
        return plantEntity;
    }

}
