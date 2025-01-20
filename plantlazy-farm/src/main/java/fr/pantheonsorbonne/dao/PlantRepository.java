package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository {
    PlantEntity save(PlantEntity plantEntity);
    PlantEntity findById(UUID id);
    Iterable<PlantEntity> findAll();
    void delete(PlantEntity plantEntity);
}
