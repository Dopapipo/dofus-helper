package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.dto.log.*;
import fr.pantheonsorbonne.entity.TickMessage;
import fr.pantheonsorbonne.model.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("dashboardService") // pour accès depuis la route Camel
public class DashboardService {

    private final Dashboard dashboard = new Dashboard();

    public void processResourceUpdate(ResourceUpdateLogDTO log) {
        dashboard.updateResource(log.getResourceType(), log.getQuantityAfter());
    }

    public void processStoreSellableSeeds(StoreSellableSeedsLogDTO log) {
        dashboard.updateSeedsForSale(log.getSeeds());
    }

    public void processStoreSellablePlant(StoreSellablePlantLogDTO log) {
        System.out.println("Plante à mettre en vente" + log.getPlantId() + log.getName() + log.getPrice());
        dashboard.updatePlantsForSale(log.getPlantId(), log.getName(), log.getPrice());
    }

    public void processStoreSoldPlant(StoreSoldPlantLogDTO log) {
        dashboard.updateSoldPlants(log.getPlantId(), log.getPrice());
    }

    public void processDeadPlant(DeadPlantLogDTO log) {
        dashboard.updateDeadPlant(log.getPlantId(), log.getName(), log.getDecompositionLevel());
    }


    public void processPlantDead(PlantDeadLogDTO log) {
        boolean removed = dashboard.removePlant(log.getId());
        if (removed) {
            System.out.printf("🪦 Plante morte retirée : %s%n", log.getId());
        } else {
            System.err.printf("⚠️ Impossible de trouver la plante à supprimer : %s%n", log.getId());
        }
        dashboard.display();
    }

    public void processPlantSold(PlantSoldLogDTO log) {
        boolean removed = dashboard.removePlant(log.getId());
        if (removed) {
            System.out.printf("🪦 Plante mise en vente : %s%n", log.getId());
        } else {
            System.err.printf("⚠️ Impossible de trouver la plante à supprimer des plantes en cours : %s%n", log.getId());
        }

        dashboard.display();
    }


    public void processPlantCreated(PlantCreatedLogDTO log) {
        dashboard.addNewPlant(log.getPlantId(), log.getName(), log.getEnergyLevel(),
                log.getWaterLevel(), log.getFertilizerLevel());
    }

    public void processPlantGrown(PlantGrownLogDTO log) {
        dashboard.markPlantAsMature(log.getPlantId());
    }

    public void processPlantUpdate(PlantUpdateLogDTO log) {
        PlantDTO plant = log.getPlant();

        if (!dashboard.plantExists(plant.getId())) {
            dashboard.addNewPlant(plant.getId(), plant.getType(), plant.getSun(), plant.getWater(), plant.getSoil());
        }

        dashboard.updatePlantStats(plant.getId(), plant.getSun(), plant.getWater(), plant.getSoil());
    }

    public void processTick(TickMessage tick) {
        String tickType = tick.getTickType().name();
        dashboard.updateTick(tickType);
    }
}
