package fr.pantheonsorbonne.dto;

import fr.pantheonsorbonne.entity.SeedEntity;
import java.util.List;

public record SeedInShopLogDTO(List<SeedEntity> seeds, LogType type) {
}
