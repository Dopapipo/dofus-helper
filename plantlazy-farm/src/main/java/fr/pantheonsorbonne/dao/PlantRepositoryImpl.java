package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.PlantEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PlantRepositoryImpl implements PlantRepository {
    @Inject
    EntityManager em;

    @Transactional
    @Override
    public PlantEntity save(PlantEntity plantEntity) {
        if (plantEntity.getId() == null) {
            em.persist(plantEntity);
            return plantEntity;
        }
        return em.merge(plantEntity);
    }

    @Transactional
    @Override
    public PlantEntity findById(UUID id) {
        return em.find(PlantEntity.class, id);
    }
    @Transactional
    @Override
    public List<PlantEntity> findAll() {
        List<PlantEntity> plants = em.createQuery("SELECT p FROM PlantEntity p", PlantEntity.class).getResultList();
        return plants.stream().map(em::merge).toList();
    }
}
