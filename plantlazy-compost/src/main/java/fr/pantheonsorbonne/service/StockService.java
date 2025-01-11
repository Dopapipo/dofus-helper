package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.ResourceType;
import fr.pantheonsorbonne.exception.InsufficientResourceException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StockService {
    @Inject
    EntityManager em;

    @Transactional
    public void addFertilizer(double amount) {
        Resource fertilizer = getOrCreateResource(ResourceType.FERTILIZER);
        fertilizer.add(amount);
        em.merge(fertilizer);
    }

    @Transactional
    public void deliverResource(ResourceType type, double amount) {
        Resource resource = getOrCreateResource(type);
        if (resource.canExtract(amount)) {
            resource.extract(amount);
            em.merge(resource);
        } else {
            throw new InsufficientResourceException(type);
        }
    }

    private Resource getOrCreateResource(ResourceType type) {
        return em.createQuery("SELECT r FROM Resource r WHERE r.type = :type", Resource.class).setParameter("type", type).getResultStream().findFirst().orElseGet(() -> {
            Resource newResource = new Resource();
            newResource.setType(type);
            newResource.setQuantity(0.0);
            if (type != ResourceType.FERTILIZER) {
                newResource.setDailyLimit(1000.0);
                newResource.setUsedToday(0.0);
            }
            em.persist(newResource);
            return newResource;
        });
    }
}