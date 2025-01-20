
package fr.pantheonsorbonne.dao;

import fr.pantheonsorbonne.entity.Seed;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class SeedDAOImpl implements SeedDAO {
    private final Map<String, Seed> seedDatabase = new HashMap<>();

    @Override
    public void saveSeed(Seed seed) {
        seedDatabase.put(seed.getType(), seed);
        System.out.println("Seed saved: " + seed.getType());
    }

    @Override
    public List<Seed> getAllSeeds() {
        return new ArrayList<>(seedDatabase.values());
    }

    @Override
    public Seed getSeedByType(String type) {
        return seedDatabase.get(type);
    }

    @Override
    public void updateSeedQuantity(String type, int newQuantity) {
        Seed seed = seedDatabase.get(type);
        if (seed == null) {
            throw new IllegalArgumentException("Seed type not found");
        }
        seed.setQuantity(newQuantity);
        System.out.println("Seed quantity updated for type " + type + ": " + newQuantity);
    }
}
