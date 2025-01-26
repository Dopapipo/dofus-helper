package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.service.StatsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import fr.pantheonsorbonne.service.ServerTickService;

@Path("/api/simulation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StopResource {

    @Inject
    @RestClient
    ServerTickService serverTickService;

    @Inject
    StatsService statsService;

    @POST
    @Path("/stop")
    public Response stopSimulation() {
        System.out.println("Received request to stop simulation");

        try {
            serverTickService.stopTicks();

            statsService.generateStats();

            return Response.ok("Simulation stopped successfully. Statistics generated").build();

        } catch (Exception e) {
            System.out.println("Error while stopping simulation or generating statistics: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error while stopping simulation or generating statistics: " + e.getMessage())
                    .build();
        }
    }
}
