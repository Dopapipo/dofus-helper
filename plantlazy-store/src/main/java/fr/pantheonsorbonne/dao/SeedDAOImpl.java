package fr.pantheonsorbonne.dao;


import fr.pantheonsorbonne.entity.SeedEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class SeedDAOImpl implements SeedDAO {

    @Inject
    EntityManager entityManager;

    @Override
    public List<SeedEntity> getAllSeeds() {
        return entityManager.createQuery("SELECT s FROM SeedEntity s", SeedEntity.class).getResultList();
    }

    @Override
    @Transactional
    public void saveSeed(SeedEntity seed) {
        entityManager.persist(seed);
    }

    @Override
    @Transactional
    public void deleteSeed(SeedEntity seed) {
        entityManager.remove(entityManager.contains(seed) ? seed : entityManager.merge(seed));
    }

    @Override
    @Transactional
    public void deleteAllSeeds() {
        entityManager.createQuery("DELETE FROM SeedEntity").executeUpdate();
    }
}
