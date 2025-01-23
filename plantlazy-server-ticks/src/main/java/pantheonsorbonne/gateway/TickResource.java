package pantheonsorbonne.gateway;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pantheonsorbonne.camel.TickPublisher;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TickResource {

    @Inject
    TickPublisher tickPublisher;

    @POST
    @Path("/tick-init")
    public Response startTickGeneration() {
        try {

            tickPublisher.startServerTick();

            return Response.ok("Tick generation initialized").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to initialize tick generation: " + e.getMessage())
                    .build();
        }
    }


    @POST
    @Path("/tick-stop")
    public Response stopTickGeneration() {
        try {
            tickPublisher.stopServerTick();

            return Response.ok("Tick generation stopped successfully").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to stop tick generation: " + e.getMessage())
                    .build();
        }
    }
}
