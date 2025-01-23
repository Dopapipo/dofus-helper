package fr.pantheonsorbonne.services;

import fr.pantheonsorbonne.dto.PlantLogDTO;

public class SendingPlantLogService {

    public void sendPlantLog(String endpoint, PlantLogDTO plantLog) {
        // Envoi du log via un mécanisme (par exemple, JMS, HTTP, etc.)
        System.out.println("Sending plant log to endpoint " + endpoint + ": " + plantLog);
    }
}
