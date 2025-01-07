package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.CompostDAO;
import fr.pantheonsorbonne.entity.DeadPlant;
import fr.pantheonsorbonne.entity.Fertilizer;
import fr.pantheonsorbonne.dto.FertilizerDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class CompostService {

    @Inject
    CompostDAO compostDAO;

    @Transactional
    public FertilizerDTO compostPlant(DeadPlant deadPlant) {
        deadPlant.setDeathDate(LocalDateTime.now());
        deadPlant.setProcessed(false);
        compostDAO.saveDeadPlant(deadPlant);

        Fertilizer fertilizer = new Fertilizer();
        fertilizer.setId(UUID.randomUUID().toString());
        fertilizer.setQuantity(deadPlant.getSize() * 2);
        fertilizer.setCreationDate(LocalDateTime.now());
        fertilizer.setDeadPlant(deadPlant);
        compostDAO.saveFertilizer(fertilizer);

        deadPlant.setProcessed(true);
        compostDAO.saveDeadPlant(deadPlant);

        FertilizerDTO fertilizerDTO = new FertilizerDTO();
        fertilizerDTO.setId(fertilizer.getId());
        fertilizerDTO.setQuantity(fertilizer.getQuantity());

        return fertilizerDTO;
    }
}