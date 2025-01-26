package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.dto.log.*;
import fr.pantheonsorbonne.entity.TickMessage;
import fr.pantheonsorbonne.model.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("dashboardService")
public class DashboardService {

    private final Dashboard dashboard = new Dashboard();

    public void processResourceUpdate(ResourceUpdateLogDTO log) {
        dashboard.updateResource(log.getResourceType(), log.getQuantityAfter());
    }

    public void processStoreSellableSeeds(StoreSellableSeedsLogDTO log) {
        dashboard.updateSeedsForSale(log.getSeeds());
    }

    public void processStoreSellablePlant(StoreSellablePlantLogDTO log) {
        dashboard.updatePlantsForSale(log.getId(), log.getPlantType(), log.getPrice());
    }

    public void processStoreSoldPlant(StoreSoldPlantLogDTO log) {
        dashboard.updateSoldPlants(log.getId(), log.getPrice());
    }


    public void processPlantDead(PlantDeadLogDTO log) {
        boolean removed = dashboard.removePlant(log.getId());
    }

    public void processPlantSold(PlantSoldLogDTO log) {
        boolean removed = dashboard.removePlant(log.getId());
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
