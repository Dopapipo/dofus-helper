package pantheonsorbonne.gateway;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pantheonsorbonne.camel.TickProducer;
import pantheonsorbonne.entity.TickType;

import java.util.concurrent.TimeUnit;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TickResource {

    @Inject
    TickProducer tickProducer;

    @POST
    @Path("/tick-init")
    public Response startTickGeneration() {
        try {

            tickProducer.startServerTick();

            return Response.ok("Tick generation initialized").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to initialize tick generation: " + e.getMessage())
                    .build();
        }
    }
}
