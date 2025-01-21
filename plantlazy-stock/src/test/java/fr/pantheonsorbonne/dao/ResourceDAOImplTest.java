package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ResourceDAOImplTest {

    @Inject
    ResourceDAO resourceDAO;

    @Inject
    EntityManager em;


    @Test
    @Transactional
    void testFindAllResources() {
        Resource resource1 = new Resource();
        resource1.setType(ResourceType.ENERGY);
        resource1.setQuantity(100.00);
        Resource resource2 = new Resource();
        resource2.setType(ResourceType.WATER);
        resource2.setQuantity(500.00);
        resourceDAO.save(resource1);
        resourceDAO.save(resource2);
        em.flush();

        Iterable<Resource> resources = resourceDAO.findAll();
        assertNotNull(resources);
        assertTrue(resources.iterator().hasNext());
    }

    @Test
    @Transactional
    void testFindByType() {
        Resource resource = new Resource();
        resource.setType(ResourceType.ENERGY);
        resource.setQuantity(100.00);
        resourceDAO.save(resource);
        em.flush();

        Resource foundResource = resourceDAO.findByType(ResourceType.ENERGY).orElse(null);
        assertNotNull(foundResource);
        assertEquals(ResourceType.ENERGY, foundResource.getType());
    }

    @Test
    @Transactional
    void testSave() {
        Resource resource = new Resource();
        resource.setType(ResourceType.ENERGY);
        resource.setQuantity(100.00);
        resourceDAO.save(resource);
        em.flush();

        Resource foundResource = resourceDAO.findByType(ResourceType.ENERGY).orElse(null);
        assertNotNull(foundResource);
        assertEquals(ResourceType.ENERGY, foundResource.getType());
    }

    @Test
    @Transactional
    void testSaveWithId() {
        Resource resource = new Resource();
        resource.setId(1L);
        resource.setType(ResourceType.ENERGY);
        resource.setQuantity(100.00);
        resourceDAO.save(resource);
        em.flush();

        Resource foundResource = resourceDAO.findByType(ResourceType.ENERGY).orElse(null);
        assertNotNull(foundResource);
        assertEquals(ResourceType.ENERGY, foundResource.getType());
    }
}
