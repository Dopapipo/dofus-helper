package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.ResourceRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "stock-service")
@Path("/api/stock")
public interface StockService {

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    Response requestResource(ResourceRequest resourceRequest);
}

