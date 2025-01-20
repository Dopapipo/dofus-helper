package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.camel.producers.SoilProducer;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlantManagerImpl implements PlantManager {
    @Inject
    SoilProducer soilProducer;
    @Inject
    PlantRessourceManager plantRessourceManager;

    @Override
    public void sendSoilFromDeadPlants(Iterable<PlantEntity> plants) {

    }

    @Override
    public void triggerPlantNourishment(Iterable<PlantEntity> plants) {
        for (PlantEntity plant : plants) {
            for (PlantStat stat : plant.getStats()) {
                if (plantNeedsNourishmentForStat(plant, stat)) {
                    int requiredResourceQuantity = stat.getOptimalRessourceQuantityToFeed();
                    plantRessourceManager.feedPlant(plant, requiredResourceQuantity, stat);
                }
            }
        }
    }

    private boolean plantNeedsNourishmentForStat(PlantEntity plant, PlantStat plantStat) {
        return plant.getRemainingTicksOfHealthyFor(plantStat) < 3;
    }
}
