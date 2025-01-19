package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.update.*;
import fr.pantheonsorbonne.entity.TickMessage;
import fr.pantheonsorbonne.model.Dashboard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;


@ApplicationScoped
@Named("dashboardService")
public class DashboardService {
    private final Dashboard dashboard = new Dashboard();


    public void processResourceUpdate(ResourceUpdateDTO resourceUpdate) {
        dashboard.updateResource(resourceUpdate.getResourceType(), resourceUpdate.getAfter());
        dashboard.display(); // Affiche l'état mis à jour du tableau de bord
    }

    public void processStoreSellableSeeds(SeedSaleUpdateDTO storeSellablePlants) {
        dashboard.updateSeedsForSale(storeSellablePlants.getSeeds());
        dashboard.display(); // Affiche l'état mis à jour du tableau de bord
    }


    public void processStoreSellablePlants(PlantSaleUpdateDTO dailyOffer) {
        dashboard.updatePlantsForSale(dailyOffer.getPlants());
        dashboard.display(); // Affiche l'état mis à jour du tableau de bord
    }

    public void processDeadPlantUpdate(DeadPlantUpdateDTO deadPlantUpdate) {
        dashboard.updateDeadPlants(deadPlantUpdate.getPlants()); // Met à jour les plantes mortes
        dashboard.display(); // Affiche l'état mis à jour du tableau de bord
    }


    public void processPlantUpdate(PlantUpdateDTO plantUpdate) {
        dashboard.updatePlantsInProgress(plantUpdate.getPlants()); // Met à jour les plantes en cours
        dashboard.display(); // Affiche l'état mis à jour du tableau de bord
    }

    public void processTick(TickMessage tick) {
        String tickType = tick.getTickType().name();
        dashboard.updateTick(tickType); // Met à jour le tableau de bord en fonction du type de tick
        dashboard.display(); // Affiche l'état mis à jour du tableau de bord
    }
}
