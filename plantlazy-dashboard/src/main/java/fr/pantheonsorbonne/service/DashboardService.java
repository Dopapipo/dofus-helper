package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.LogMessage;
import fr.pantheonsorbonne.dto.PlantUpdateDTO;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.model.Dashboard;
import fr.pantheonsorbonne.model.PlantData;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class DashboardService {
    private final Dashboard dashboard = new Dashboard();

    public void processLog(LogMessage logMessage) {
        // Identifier le type de log et traiter le payload
        switch (logMessage.getType()) {
            case "RESOURCE_UPDATE":
                ResourceUpdateDTO resourceUpdate = castPayload(logMessage.getPayload(), ResourceUpdateDTO.class);
                updateResources(resourceUpdate);
                break;

            case "PLANT_UPDATE":
                PlantUpdateDTO plantUpdate = castPayload(logMessage.getPayload(), PlantUpdateDTO.class);
                updatePlant(plantUpdate);
                break;

            case "PLANT_SALE":
                PlantSaleDTO plantSale = castPayload(logMessage.getPayload(), PlantSaleDTO.class);
                addPlantForSale(plantSale);
                break;

            case "PLANT_DEAD":
                String deadPlantId = (String) logMessage.getPayload();
                markPlantAsDead(deadPlantId);
                break;

            default:
                System.out.println("Type de message inconnu : " + logMessage.getType());
        }
    }

    public void updateResources(ResourceUpdateDTO update) {
        dashboard.getResources().setWater(update.getUpdatedWater());
        dashboard.getResources().setEnergy(update.getUpdatedEnergy());
        dashboard.getResources().setFertilizer(update.getUpdatedFertilizer());
        dashboard.getResources().setMoney(update.getUpdatedMoney());
    }

    public void updatePlant(PlantUpdateDTO update) {
        PlantData plant = dashboard.getPlantsInProgress()
                .computeIfAbsent(update.getPlantId(), id -> new PlantData());
        plant.setName(update.getPlantName());
        plant.setWateringLevel(update.getUpdatedWateringLevel());
        plant.setEnergyLevel(update.getUpdatedEnergyLevel());
        plant.setFertilizerLevel(update.getUpdatedFertilizerLevel());
        plant.setHealthLevel(update.getUpdatedHealthLevel());
    }

    public void addPlantForSale(PlantSaleDTO sale) {
        PlantData plant = new PlantData();
        plant.setName(sale.getPlantName());
        plant.setPrice(sale.getUpdatedPrice());
        plant.setSalesRate(sale.getUpdatedSalesRate());
        dashboard.getPlantsForSale().put(sale.getPlantId(), plant);
    }

    public void markPlantAsDead(String plantId) {
        dashboard.incrementDeadPlants(plantId);
    }

    public void incrementTick() {
        dashboard.incrementTick();
    }

    public void displayDashboard() {
        System.out.println("Tableau de bord mis à jour :");
        dashboard.display();
    }

    // Méthode utilitaire pour caster le payload
    private <T> T castPayload(Object payload, Class<T> type) {
        if (type.isInstance(payload)) {
            return type.cast(payload);
        } else {
            throw new IllegalArgumentException("Payload ne correspond pas au type attendu : " + type.getSimpleName());
        }
    }
}
