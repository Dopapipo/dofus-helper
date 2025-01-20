package fr.pantheonsorbonne.resource;

import fr.pantheonsorbonne.dto.PlantSaleDTO;
import fr.pantheonsorbonne.entity.PlantEntity;
import fr.pantheonsorbonne.entity.enums.PlantType;
import fr.pantheonsorbonne.services.PlantService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/plants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlantResource {

    @Inject
    PlantService plantService;

    @GET
    public List<PlantSaleDTO> getAvailablePlants() {
        List<PlantEntity> plants = plantService.getAvailablePlants();
        return plants.stream()
                .map(plant -> new PlantSaleDTO(plant.getType(), plant.getPrice(), plant.getQuantity()))
                .collect(Collectors.toList());
    }

    @POST
    @Path("/sell/{type}/{quantity}")
    public Response sellPlant(@PathParam("type") PlantType type, @PathParam("quantity") int quantity) {
        try {
            plantService.sellPlant(type, quantity);
            return Response.ok("Plant sold successfully").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
