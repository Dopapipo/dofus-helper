package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PlantRepositoryImpl implements PlantRepository {
    @Inject
    EntityManager em;

    @Transactional
    @Override
    public PlantEntity save(PlantEntity plantEntity) {
        return em.merge(plantEntity);

    }

    @Override
    public PlantEntity findById(UUID id) {
        return em.find(PlantEntity.class, id);
    }

    @Override
    public Iterable<PlantEntity> findAll() {
        return em.createQuery("SELECT p FROM PlantEntity p", PlantEntity.class).getResultList();
    }

    @Override
    public void delete(PlantEntity plantEntity) {
        em.remove(plantEntity);
    }
}
