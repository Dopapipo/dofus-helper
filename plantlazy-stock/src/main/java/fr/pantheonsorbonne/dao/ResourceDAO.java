package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Resource;
import fr.pantheonsorbonne.entity.enums.ResourceType;

import java.util.List;

public interface ResourceDAO {

    Resource findByType(ResourceType type);

    List<Resource> findAll();

    Resource save(Resource resource);
}