package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.entity.PlantEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlantService {
    @Inject
    PlantRepository plantRepository;
    @Inject
    PlantManager plantManager;

    public void processPlantLifecycle() {
        Iterable<PlantEntity> plants = plantRepository.findAll();
        plantManager.triggerPlantGrowth(plants);
        this.takeCareOfPlants();
    }

    public void processDailyCycle() {
        Iterable<PlantEntity> plants = plantRepository.findAll();
        plantManager.sendDeadPlants(plants);
    }

    public void takeCareOfPlants() {
        Iterable<PlantEntity> plants = plantRepository.findAll();
        plantManager.triggerPlantNourishment(plants);
    }
}
