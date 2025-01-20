package fr.pantheonsorbonne.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/ticks")
@RegisterRestClient(configKey = "server-tick-service")
public interface ServerTickService {

    @POST
    @Path("/start")
    void startTicks();
}
