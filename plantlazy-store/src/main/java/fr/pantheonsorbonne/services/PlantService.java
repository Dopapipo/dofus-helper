package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;

import java.util.List;

public interface PlantService {
    void sellPlants();

    void savePlant(PlantSaleDTO plantSaleDTO);
}
