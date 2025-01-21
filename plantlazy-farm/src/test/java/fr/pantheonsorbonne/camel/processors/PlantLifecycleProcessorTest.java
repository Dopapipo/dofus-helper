package fr.pantheonsorbonne.camel.processors;

import fr.pantheonsorbonne.dto.TickMessage;
import fr.pantheonsorbonne.dto.TickType;
import fr.pantheonsorbonne.service.PlantService;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
public class PlantLifecycleProcessorTest {

    @Mock
    PlantService plantService;

    @InjectMocks
    PlantLifecycleProcessor plantLifecycleProcessor;

    @Mock
    Exchange exchange;

    @Mock
    Message message;

    TickMessage tickMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessHourlyTick() {
        tickMessage = new TickMessage(TickType.HOURLY, System.currentTimeMillis());

        when(exchange.getIn()).thenReturn(message);
        when(message.getBody(TickMessage.class)).thenReturn(tickMessage);


        plantLifecycleProcessor.process(exchange);


        verify(plantService, times(1)).processPlantLifecycle();
    }
 @Test
    void testProcessDailyTick() {
     tickMessage = new TickMessage(TickType.DAILY, System.currentTimeMillis());
        when(exchange.getIn()).thenReturn(message);
        when(message.getBody(TickMessage.class)).thenReturn(tickMessage);


        plantLifecycleProcessor.process(exchange);


        verify(plantService, times(1)).processDailyCycle();
    }
}
