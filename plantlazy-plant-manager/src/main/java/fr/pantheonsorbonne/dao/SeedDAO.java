
package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Seed;
import java.util.List;

public interface SeedDAO {
    void saveSeed(Seed seed);
    List<Seed> getAllSeeds();
    Seed getSeedByType(String type);
    void updateSeedQuantity(String type, int newQuantity);
}
