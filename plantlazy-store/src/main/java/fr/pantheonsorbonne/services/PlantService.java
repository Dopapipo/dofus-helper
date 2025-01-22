package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.List;

public interface PlantService {
    List<PlantEntity> getAvailablePlants();

    void sellPlant(PlantSaleDTO plantSaleDTO);

    void savePlant(PlantSaleDTO plantSaleDTO);
}
