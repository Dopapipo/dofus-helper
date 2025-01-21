
package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Plant;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlantDAOImpl implements PlantDAO {
    private final List<Plant> plantDatabase = new ArrayList<>();

    @Override
    public void savePlant(Plant plant) {
        plantDatabase.add(plant);
    }

    @Override
    public List<Plant> getAllPlants() {
        return new ArrayList<>(plantDatabase);
    }

    @Override
    public Plant getPlantById(int id) {
        if (id < 0 || id >= plantDatabase.size()) {
            throw new IllegalArgumentException("Invalid plant ID");
        }
        return plantDatabase.get(id);
    }

    @Override
    public void deletePlant(int id) {
        if (id < 0 || id >= plantDatabase.size()) {
            throw new IllegalArgumentException("Invalid plant ID");
        }
        Plant removed = plantDatabase.remove(id);
    }
}
