
package fr.pantheonsorbonne.plantlazy.manager.service;

import fr.pantheonsorbonne.plantlazy.manager.dto.MoneyDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MoneyServiceTest {

    @Test
    public void testProcessMoney() {
        MoneyService service = new MoneyService();
        MoneyDTO dto = new MoneyDTO(250.75);

        // Verify that no exception is thrown during processing
        assertDoesNotThrow(() -> service.processMoney(dto));
    }
}
