package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.update.*;
import fr.pantheonsorbonne.entity.TickMessage;
import fr.pantheonsorbonne.model.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;


@ApplicationScoped
@Named("dashboardService") // pour accéder à la méthode depuis le route Camel
public class DashboardService {


    private final Dashboard dashboard = new Dashboard();


    public void processResourceUpdate(ResourceUpdateDTO resourceUpdate) {
        dashboard.updateResource(resourceUpdate.getResourceType(), resourceUpdate.getAfter());
        dashboard.display();
    }

    public void processStoreSellableSeeds(SeedSaleUpdateDTO storeSellablePlants) {
        dashboard.updateSeedsForSale(storeSellablePlants.getSeeds());
        dashboard.display();
    }


    public void processStoreSellablePlants(PlantSaleUpdateDTO dailyOffer) {
        dashboard.updatePlantsForSale(dailyOffer.getPlants());
        dashboard.display();
    }

    public void processDeadPlantUpdate(DeadPlantUpdateDTO deadPlantUpdate) {
        dashboard.updateDeadPlants(deadPlantUpdate.getPlants());
        dashboard.display();
    }


    public void processPlantUpdate(LivePlantUpdateDTO plantUpdate) {
        dashboard.updatePlantStats(
                plantUpdate.getPlantId(),
                plantUpdate.getEnergyLevel(),
                plantUpdate.getFertilizerLevel(),
                plantUpdate.getWaterLevel(),
                plantUpdate.getGrowthLevel()
        );
        dashboard.display();
    }

    public void processPlantDeleted(LivePlantDeletedUpdateDTO plantDeleted) {
        dashboard.markPlantAsDead(plantDeleted.getPlantId());
        dashboard.display();
    }

    public void processPlantGrown(LivePlantGrownUpdateDTO plantGrown) {
        dashboard.markPlantAsGrown(plantGrown.getPlantId());
        dashboard.display();
    }

    public void processPlantCreated(LivePlantCreatedUpdateDTO plantCreated) {
        dashboard.addNewPlant(
                plantCreated.getPlantId(),
                plantCreated.getPlantName(),
                plantCreated.getEnergyLevel(),
                plantCreated.getFertilizerLevel(),
                plantCreated.getWaterLevel(),
                plantCreated.getGrowthLevel()
        );
        dashboard.display();

    }


    public void processTick(TickMessage tick) {
        String tickType = tick.getTickType().name();
        dashboard.updateTick(tickType);
        dashboard.display();
    }
}
