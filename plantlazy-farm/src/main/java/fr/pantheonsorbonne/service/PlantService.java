package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.entity.PlantEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlantService {
    @Inject
    PlantRepository plantRepository;
    public void processPlantLifecycle() {
        Iterable<PlantEntity> plants = plantRepository.findAll();
        for (PlantEntity plant : plants) {
            plant.grow();
            plantRepository.save(plant);
        }
    }
    public void processDailyCycle() {

    }
}
