package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dto.SeedLogDTO;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.entity.enums.SeedQuality;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SeedNotificationService {

    @Inject
    SendingSeedLogService sendingSeedLogService;

    @ConfigProperty(name = "log.endpoint")
    String logEndpoint;

    public void notifySeedUpdate(PlantType seedType, long quantity) {
        SeedLogDTO seedPriceDTO = new SeedLogDTO(seedType, quantity, "STORE_SELLABLE_SEEDS");
        sendingSeedLogService.sendSeedLog(logEndpoint, seedPriceDTO);
    }
}