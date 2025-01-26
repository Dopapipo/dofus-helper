package fr.pantheonsorbonne.mapper;

import fr.pantheonsorbonne.dto.LogMessagePlantCreatedOrUpdated;
import fr.pantheonsorbonne.dto.LogMessagePlantDiedOrSold;
import fr.pantheonsorbonne.entity.enums.LogType;
import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.entity.PlantEntity;

public class PlantMapper {

    public static LogMessagePlantDiedOrSold toPlantDiedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessagePlantDiedOrSold(LogType.PLANT_DEAD, plantDTO.getId());
    }

    public static LogMessagePlantDiedOrSold toPlantSoldLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessagePlantDiedOrSold(LogType.PLANT_SOLD, plantDTO.getId());
    }

    public static LogMessagePlantCreatedOrUpdated toPlantCreatedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessagePlantCreatedOrUpdated(LogType.PLANT_CREATED, plantDTO.getId(), plantDTO.type(), plantDTO.water().getValue(), plantDTO.sun().getValue(), plantDTO.soil().getValue());
    }

    public static LogMessagePlantCreatedOrUpdated toPlantUpdatedLog(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        return new LogMessagePlantCreatedOrUpdated(LogType.PLANT_UPDATE, plantDTO.getId(), plantDTO.type(), plantDTO.water().getValue(), plantDTO.sun().getValue(), plantDTO.soil().getValue());
    }

    public static PlantDTO toPlantDTO(PlantEntity plant) {
        return new PlantDTO(plant);
    }

    public static PlantEntity toPlantEntity(PlantDTO plantDTO) {
        return new PlantEntity(plantDTO);
    }

}
