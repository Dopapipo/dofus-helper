package fr.pantheonsorbonne.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dao.EventLogRepository;
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
            int totalPlantsGrown = 0;
            int totalResourceUpdates = 0;

            Map<String, Integer> resourcesUsed = new HashMap<>();

            for (EventLog log : allLogs) {
                switch (log.getEventType()) {

                    case "PLANT_CREATED" -> {
                        PlantCreatedLogDTO dto = objectMapper.readValue(log.getPayload(), PlantCreatedLogDTO.class);
                        totalPlantsCreated++;
                    }

                    case "PLANT_DEAD" -> {
                        PlantDeadLogDTO dto = objectMapper.readValue(log.getPayload(), PlantDeadLogDTO.class);
                        totalPlantsDead++;
                    }

                    case "PLANT_GROWN" -> {
                        PlantGrownLogDTO dto = objectMapper.readValue(log.getPayload(), PlantGrownLogDTO.class);
                        totalPlantsGrown++;
                    }

                    case "RESOURCE_UPDATE" -> {
                        ResourceUpdateLogDTO dto = objectMapper.readValue(log.getPayload(), ResourceUpdateLogDTO.class);
                        totalResourceUpdates++;

                        String resourceType = dto.getResourceType();
                        int quantityUsed = dto.getQuantityBefore() - dto.getQuantityAfter();
                        resourcesUsed.merge(resourceType, quantityUsed, Integer::sum);
                    }


                    default -> {
                    }
                }
            }

            System.out.println("=== STATISTIQUES DE LA SIMULATION ===");
            System.out.println("Total des plantes créées : " + totalPlantsCreated);
            System.out.println("Total des plantes mortes : " + totalPlantsDead);
            System.out.println("Total des plantes arrivées à maturité : " + totalPlantsGrown);
            System.out.println("Total des mises à jour de ressources : " + totalResourceUpdates);
            System.out.println("Ressources utilisées : " + resourcesUsed);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération des statistiques", e);
        }
    }
}
