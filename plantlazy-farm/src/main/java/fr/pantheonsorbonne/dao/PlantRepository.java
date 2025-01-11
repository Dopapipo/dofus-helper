package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<PlantEntity, UUID> {
}
