package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dao.PlantDAO;
import fr.pantheonsorbonne.dto.LogType;
import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.dto.PlantSaleLogDTO;
import fr.pantheonsorbonne.dto.SeedLogDTO;
import fr.pantheonsorbonne.entity.SeedEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@ApplicationScoped
public class SeedNotificationService {

    @Inject
    SendingSeedLogService sendingSeedLogService;

    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;

    public void notifySeedUpdate(List<SeedEntity> seeds) {
        // Créer un SeedLogDTO avec la liste des graines
        SeedLogDTO seedLogDTO = new SeedLogDTO(seeds, LogType.STORE_SELLABLE_SEEDS);

        // Envoyer le log via le service
        sendingSeedLogService.sendSeedLog(logEndpoint, seedLogDTO);
    }



    public void notifyPlantSale(PlantType plantType, double price) {
        PlantSaleLogDTO plantLog = new PlantSaleLogDTO(plantType, price, LogType.STORE_SOLD_PLANT);
        sendingSeedLogService.sendPlantLog(logEndpoint, plantLog);
    }
}
