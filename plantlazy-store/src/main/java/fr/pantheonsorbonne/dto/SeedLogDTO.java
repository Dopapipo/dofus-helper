package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.SeedEntity;
import java.util.List;

public record SeedLogDTO(List<SeedEntity> seeds, LogType type) {
}
