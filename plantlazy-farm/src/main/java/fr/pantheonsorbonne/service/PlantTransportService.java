package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

@ApplicationScoped
public class PlantTransportService {
    @Inject
    ProducerTemplate producerTemplate;

    public void send(PlantEntity plant) {
        PlantDTO plantDTO = PlantMapper.toPlantDTO(plant);
        producerTemplate.sendBody("direct:plantQueue", plantDTO);
    }

}
