package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.DeadPlant;
import fr.pantheonsorbonne.entity.Fertilizer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CompostDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public DeadPlant saveDeadPlant(DeadPlant plant) {
        em.persist(plant);
        return plant;
    }

    @Transactional
    public Fertilizer saveFertilizer(Fertilizer fertilizer) {
        em.persist(fertilizer);
        return fertilizer;
    }

    public DeadPlant findDeadPlant(String id) {
        return em.find(DeadPlant.class, id);
    }
}