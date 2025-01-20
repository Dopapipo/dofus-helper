package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.camel.client.ResourceStockClient;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.exception.ResourceRequestDeniedException;
import fr.pantheonsorbonne.entity.plant.stat.PlantStat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
public class PlantRessourceManagerImpl implements PlantRessourceManager {
    @Inject
    ResourceStockClient resourceStockClient;

    private static final Logger logger = LoggerFactory.getLogger(PlantRessourceManager.class);

    @Override
    public PlantEntity feedPlant(PlantEntity plant, int quantity, PlantStat stat) {
        try {
            resourceStockClient.requestResource(stat.getType(), quantity);
            plant.feed(stat.getType(), quantity);
        } catch (ResourceRequestDeniedException e) {
            logger.warn("Resource request denied for {}: {}", stat.getType(), e.getMessage());
        }
        return plant;
    }}

