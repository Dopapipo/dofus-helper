package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dao.PlantRepository;
import fr.pantheonsorbonne.dto.PlantDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.mapper.PlantMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@ApplicationScoped
public class SoldPlantProcessor implements Processor {
    @Inject
    PlantRepository plantRepository;
    @Override
    public void process(Exchange exchange) throws Exception {
        PlantDTO plantDTO = exchange.getIn().getBody(PlantDTO.class);
        PlantEntity plantEntity = PlantMapper.toPlantEntity(plantDTO);
        plantEntity.setSold(true);
        plantRepository.save(plantEntity);
    }
}
