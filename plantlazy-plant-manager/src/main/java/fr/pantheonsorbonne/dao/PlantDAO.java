
package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Plant;

import java.util.List;


public interface PlantDAO {
    void savePlant(Plant plant);
    List<Plant> getAllPlants();
    Plant getPlantById(int id);
    void deletePlant(int id);
}
