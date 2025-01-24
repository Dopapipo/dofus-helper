package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dto.PlantFromFarmDTO;

public interface PlantService {
    void sellPlants();

    void putPlantInShop(PlantFromFarmDTO plantFromFarmDTO);
}
