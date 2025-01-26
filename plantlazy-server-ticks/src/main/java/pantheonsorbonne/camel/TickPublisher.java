package pantheonsorbonne.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pantheonsorbonne.entity.TickMessage;
import pantheonsorbonne.entity.TickType;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TickPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(TickPublisher.class);

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty(name = "tick.hourly.interval")
    long hourlyInterval;

    @ConfigProperty(name = "tick.daily.interval")
    long dailyInterval;

    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(2);


    public void startServerTick() {
        scheduler.scheduleAtFixedRate(() -> sendTick(TickType.HOURLY), 0L, hourlyInterval, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(() -> sendTick(TickType.DAILY), 0L, dailyInterval, TimeUnit.MILLISECONDS);
    }

    public void stopServerTick() {
        scheduler.shutdown();
    }


    public void sendTick(TickType tickType) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            long timestamp = System.currentTimeMillis();
            TickMessage tickMessage = new TickMessage(tickType, timestamp);

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectMessage message = context.createObjectMessage(objectMapper.writeValueAsString(tickMessage));

            context.createProducer().send(context.createTopic(tickEndpoint), message);

        } catch (Exception e) {
            LOGGER.error("Unexpected error while publishing {} tick. Error: {}", tickType, e.getMessage(), e);
        }
    }

}

