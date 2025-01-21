package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.EventLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class EventLogRepository {

    @PersistenceContext
    EntityManager em;

    public void persist(EventLog event) {
        em.persist(event);
    }

    public EventLog findById(Long id) {
        return em.find(EventLog.class, id);
    }

    public List<EventLog> findAll() {
        return em.createQuery("SELECT e FROM EventLog e", EventLog.class).getResultList();
    }

    public List<EventLog> findByEventType(String eventType) {
        return em.createQuery("SELECT e FROM EventLog e WHERE e.eventType = :type", EventLog.class)
                .setParameter("type", eventType)
                .getResultList();
    }
}
