package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.ResourceType;
import java.util.List;
import java.util.Optional;

public interface ResourceDAO {

    Optional<Resource> findByType(ResourceType type);

    List<Resource> findAll();

    Resource save(Resource resource);
}