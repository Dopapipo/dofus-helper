
package fr.pantheonsorbonne.plantlazy.manager.service;

import fr.pantheonsorbonne.plantlazy.manager.dto.SeedDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SeedServiceTest {

    @Test
    public void testProcessSeeds() {
        SeedService service = new SeedService();
        SeedDTO dto = new SeedDTO("Flower", 100);

        // Verify that no exception is thrown during processing
        assertDoesNotThrow(() -> service.processSeeds(dto));
    }
}
