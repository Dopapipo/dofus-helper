
package fr.pantheonsorbonne.plantlazy.manager.service;

import fr.pantheonsorbonne.plantlazy.manager.dto.DeadPlantDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DeadPlantServiceTest {

    @Test
    public void testProcessDeadPlant() {
        DeadPlantService service = new DeadPlantService();
        DeadPlantDTO dto = new DeadPlantDTO("Plant123");

        // Verify that no exception is thrown during processing
        assertDoesNotThrow(() -> service.processDeadPlant(dto));
    }
}
