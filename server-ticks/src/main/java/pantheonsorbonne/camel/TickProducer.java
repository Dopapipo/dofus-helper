package pantheonsorbonne.camel;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import pantheonsorbonne.entity.TickMessage;
import pantheonsorbonne.entity.TickType;

@ApplicationScoped
public class TickProducer {

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty(name = "tick.hourly.interval")
    long hourlyInterval;

    @ConfigProperty(name = "tick.daily.interval")
    long dailyInterval;

    @ConfigProperty(name = "tick.endpoint")
    String tickEndpoint;

    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(2);

    void onStart(@Observes StartupEvent ev) {
        scheduler.scheduleAtFixedRate(() -> sendTick(TickType.HOURLY), 0L, hourlyInterval, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(() -> sendTick(TickType.DAILY), 0L, dailyInterval, TimeUnit.MILLISECONDS);
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    private void sendTick(TickType tickType) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            long timestamp = System.currentTimeMillis();
            TickMessage tickMessage = new TickMessage(tickType, timestamp);
            ObjectMessage message = context.createObjectMessage(tickMessage);

            context.createProducer().send(context.createQueue(tickEndpoint), message);
            System.out.println("Sent " + tickType + " tick to " + tickEndpoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
