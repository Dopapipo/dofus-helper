package fr.pantheonsorbonne.dao;


import fr.pantheonsorbonne.entity.SeedEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface SeedRepository {
    void save(SeedEntity seed);
}
