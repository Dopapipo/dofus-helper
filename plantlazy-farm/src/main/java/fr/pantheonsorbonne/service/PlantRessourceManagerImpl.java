package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.camel.client.ResourceStockClient;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlantRessourceManagerImpl implements PlantRessourceManager {
    @Inject
    ResourceStockClient resourceStockClient;

    @Override
    public PlantEntity feedPlant(PlantEntity plant, int quantity, PlantStat stat) {

        if (resourceStockClient.requestResource(stat.getType(), quantity)) {
            plant.feed(stat.getType(), quantity);
        }
        return plant;
    }

}

