package fr.pantheonsorbonne.service;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "server-tick-service")
@Path("/api")
public interface ServerTickService {

    @POST
    @Path("/tick-init")
    void startTicks();
}
