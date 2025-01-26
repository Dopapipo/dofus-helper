package fr.pantheonsorbonne.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dao.EventLogRepository;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.dto.log.*;
import fr.pantheonsorbonne.entity.EventLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class StatsService {

    @Inject
    EventLogRepository repository;

    @Inject
    ObjectMapper objectMapper;

    public void generateStats() {
        try {
            List<EventLog> allLogs = repository.findAll();

            int totalPlantsCreated = 0;
            int totalPlantsDead = 0;
            int totalPlantsUpdated = 0;
            int totalSeedsSold = 0;
            int totalPlantsSold = 0;

            Map<String, Integer> resourcesUsed = new HashMap<>();
            Map<String, Integer> plantsSoldByType = new HashMap<>();
            Map<String, Integer> seedsSoldByType = new HashMap<>();

            for (EventLog log : allLogs) {
                switch (log.getEventType()) {
                    case "PLANT_UPDATE" -> {
                        PlantUpdateLogDTO dto = objectMapper.readValue(log.getPayload(), PlantUpdateLogDTO.class);
                        totalPlantsUpdated++;
                    }

                    case "RESOURCE_UPDATE" -> {
                        ResourceUpdateLogDTO dto = objectMapper.readValue(log.getPayload(), ResourceUpdateLogDTO.class);

                        int quantityBefore = dto.getQuantityBefore();
                        int quantityAfter = dto.getQuantityAfter();

                        if (quantityBefore > quantityAfter) {
                            int quantityUsed = quantityBefore - quantityAfter;
                            resourcesUsed.merge(dto.getResourceType(), quantityUsed, Integer::sum);
                        }
                    }

                    case "PLANT_DEAD" -> {
                        PlantDeadLogDTO dto = objectMapper.readValue(log.getPayload(), PlantDeadLogDTO.class);
                        totalPlantsDead++;
                    }

                    case "STORE_SELLABLE_PLANT" -> {
                        StoreSellablePlantLogDTO dto = objectMapper.readValue(log.getPayload(), StoreSellablePlantLogDTO.class);
                        totalPlantsSold++;

                        String plantType = dto.getPlantType();
                        plantsSoldByType.merge(plantType, 1, Integer::sum);
                    }

                    case "STORE_SELLABLE_SEEDS" -> {
                        StoreSellableSeedsLogDTO dto = objectMapper.readValue(log.getPayload(), StoreSellableSeedsLogDTO.class);
                        totalSeedsSold += dto.getSeeds().size();

                        for (SeedDTO seed : dto.getSeeds()) {
                            String seedType = seed.getType();
                            seedsSoldByType.merge(seedType, 1, Integer::sum);
                        }
                    }
                }
            }

            System.out.println("=== STATISTIQUES DE LA SIMULATION ===");
            System.out.println("Total des plantes mises à jour : " + totalPlantsUpdated);
            System.out.println("Total des plantes mortes : " + totalPlantsDead);
            System.out.println("Ressources utilisées : " + resourcesUsed);
            System.out.println("Total des graines vendues : " + totalSeedsSold);
            System.out.println("Ventes par type de graine : " + seedsSoldByType);
            System.out.println("Total des plantes vendues : " + totalPlantsSold);
            System.out.println("Ventes par type de plante : " + plantsSoldByType);
            System.out.println("=== FIN DES STATISTIQUES ===");

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération des statistiques", e);
        }
    }


}
