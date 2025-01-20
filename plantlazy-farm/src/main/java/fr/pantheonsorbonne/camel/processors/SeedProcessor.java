package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.LogMessage;
import fr.pantheonsorbonne.dto.SeedDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.mapper.PlantMapper;
import fr.pantheonsorbonne.service.SeedService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
@ApplicationScoped
public class SeedProcessor implements Processor {

    @Inject
    SeedService seedService;
    @Override
    public void process(Exchange exchange) throws Exception {
        SeedDTO seedDTO = exchange.getIn().getBody(SeedDTO.class);
        PlantEntity plant = seedService.growSeed(seedDTO);
        LogMessage plantCreated = PlantMapper.toPlantCreatedLog(plant);
        exchange.getIn().setBody(plantCreated);
    }
}

