package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.camel.client.ResourceStockClient;
import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlantRessourceManagerImpl implements PlantRessourceManager {
    @Inject
    ResourceStockClient resourceStockClient;

    @Inject
    LogService logService;

    @Inject
    PlantRepository plantRepository;
    @Override
    public void feedPlant(PlantEntity plant, int quantity, PlantStat stat) {

        if (resourceStockClient.requestResource(stat.getType(), quantity)) {
            plant.feed(stat.getType(), quantity);
            PlantEntity plantUpdated = plantRepository.save(plant);
            logService.sendLog(PlantMapper.toPlantUpdatedLog(plantUpdated));
        }
    }

}

