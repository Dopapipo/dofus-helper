
package fr.pantheonsorbonne.plantlazy.manager.service;

import fr.pantheonsorbonne.plantlazy.manager.dto.SeedPriceDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SeedPriceServiceTest {

    @Test
    public void testProcessSeedPrice() {
        SeedPriceService service = new SeedPriceService();
        SeedPriceDTO dto = new SeedPriceDTO("Cactus", 12.5);

        // Verify that no exception is thrown during processing
        assertDoesNotThrow(() -> service.processSeedPrice(dto));
    }
}
