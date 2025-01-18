package fr.pantheonsorbonne.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.dto.LogMessage;
import fr.pantheonsorbonne.dto.ResourceUpdateDTO;
import fr.pantheonsorbonne.dto.PlantUpdateDTO;
import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.service.DashboardService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.LinkedHashMap;

@ApplicationScoped
public class LogRoute extends RouteBuilder {

    @Inject
    DashboardService dashboardService;

    @Override
    public void configure() {
        // Configurer Jackson pour désérialiser en LogMessage
        JacksonDataFormat logFormat = new JacksonDataFormat(LogMessage.class);

        from("file://log?noop=true") // Consomme les fichiers JSON depuis le dossier "log"
                .unmarshal(logFormat) // Désérialise chaque fichier en un LogMessage
                .process(exchange -> {
                    // Récupère le message désérialisé
                    LogMessage logMessage = exchange.getIn().getBody(LogMessage.class);

                    // Traitement basé sur le type du message
                    switch (logMessage.getType()) {
                        case "RESOURCE_UPDATE":
                            // Désérialiser le payload en ResourceUpdateDTO
                            ResourceUpdateDTO resourceUpdate = convertPayload(logMessage.getPayload(), ResourceUpdateDTO.class);
                            dashboardService.updateResources(resourceUpdate);
                            break;

                        case "PLANT_UPDATE":
                            // Désérialiser le payload en PlantUpdateDTO
                            PlantUpdateDTO plantUpdate = convertPayload(logMessage.getPayload(), PlantUpdateDTO.class);
                            dashboardService.updatePlant(plantUpdate);
                            break;

                        case "PLANT_SALE":
                            // Désérialiser le payload en PlantSaleDTO
                            PlantSaleDTO plantSale = convertPayload(logMessage.getPayload(), PlantSaleDTO.class);
                            dashboardService.addPlantForSale(plantSale);
                            break;

                        case "PLANT_DEAD":
                            // Le payload est une simple chaîne dans ce cas
                            String deadPlantId = (String) logMessage.getPayload();
                            dashboardService.markPlantAsDead(deadPlantId);
                            break;

                        case "TICK":
                            dashboardService.incrementTick();
                            break;

                        default:
                            System.out.println("Type de message inconnu : " + logMessage.getType());
                    }

                    // Incrémenter le tick et afficher le tableau de bord
                    dashboardService.displayDashboard();
                })
                .log("Log traité : ${body}"); // Journaliser le message traité
    }

    /**
     * Méthode utilitaire pour convertir un payload générique en un type spécifique.
     */
    private <T> T convertPayload(Object payload, Class<T> targetType) {
        if (targetType.isInstance(payload)) {
            return targetType.cast(payload);
        } else if (payload instanceof LinkedHashMap) {
            // Désérialiser manuellement à partir de LinkedHashMap si nécessaire
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(payload, targetType);
        } else {
            throw new IllegalArgumentException("Impossible de convertir le payload en type : " + targetType.getSimpleName());
        }
    }
}
