package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.service.InitService;
import fr.pantheonsorbonne.dto.InitMoneyDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/simulation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InitResource {

    @Inject
    InitService initService;

    @POST
    @Path("/init")
    public Response initializeSimulation(InitMoneyDTO initMoneyDTO) {
        System.out.println("Received request to initialize simulation with: " + initMoneyDTO);

        try {
            if (initMoneyDTO.getMoney() == null || initMoneyDTO.getMoney() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Money must be greater than 0 and not null")
                        .build();
            }

            initService.initializeSimulation(initMoneyDTO.getMoney());
            return Response.ok("Simulation initialized successfully").build();

        } catch (Exception e) {
            System.out.println("Unexpected error while initializing simulation: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unexpected error: " + e.getMessage())
                    .build();
        }
    }
}