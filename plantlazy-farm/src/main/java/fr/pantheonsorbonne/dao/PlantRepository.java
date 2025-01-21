package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import java.util.List;
import java.util.UUID;

public interface PlantRepository {
    PlantEntity save(PlantEntity plantEntity);
    PlantEntity findById(UUID id);
    List<PlantEntity> findAll();
    void delete(PlantEntity plantEntity);
}
