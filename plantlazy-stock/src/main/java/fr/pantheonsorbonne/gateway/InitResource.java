package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.InitRequestDTO;
import fr.pantheonsorbonne.service.ResourceInitializerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/api/stock-init")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InitResource {

    @Inject
    ResourceInitializerService resourceInitializerService;

    @POST
    public Response initializeResources(InitRequestDTO initRequest) {
        resourceInitializerService.initializeResources(initRequest.money());
        return Response.ok().build();
    }
}