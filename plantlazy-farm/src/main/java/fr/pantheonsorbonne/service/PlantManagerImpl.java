package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlantManagerImpl implements PlantManager {
    @Inject
    PlantTransportService plantTransportService;
    @Inject
    LogService logService;
    @Inject
    PlantRessourceManager plantRessourceManager;
    @Inject
    PlantRepository plantRepository;

    @Override
    public void sendSoilFromDeadPlants(Iterable<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            if (plant.isDead()) {
                plantTransportService.send(plant);
                logService.sendLog(PlantMapper.toPlantDiedLog(plant));
                plantRepository.delete(plant);
            }
        }
    }

    @Override
    public void triggerPlantNourishment(Iterable<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            for (PlantStat stat : plant.getStats()) {
                if (plantNeedsNourishmentForStat(plant, stat)) {
                    int requiredResourceQuantity = stat.getOptimalRessourceQuantityToFeed();
                    PlantEntity updatedPlant = plantRessourceManager.feedPlant(plant, requiredResourceQuantity, stat);
                    plantRepository.save(updatedPlant);
                    logService.sendLog(PlantMapper.toPlantUpdatedLog(updatedPlant));
                }
            }
        }
    }

    private boolean plantNeedsNourishmentForStat(PlantEntity plant, PlantStat plantStat) {
        return (!plant.isDead() && plant.getRemainingTicksOfHealthyFor(plantStat) < 3);
    }
}
