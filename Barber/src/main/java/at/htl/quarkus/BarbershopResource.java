package at.htl.quarkus;

import at.htl.entity.BarberShop;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.print.DocFlavor;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/barbershop")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BarbershopResource {

    @Inject
    BarbershopService service;

    @GET
    public Response get(){
        List<BarberShop> shops = service.getAll();
        if(shops == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
         return  Response.ok().entity(shops).build();
    }

    @GET
    @Path("/{name}")
    public Response getByName(@PathParam("name") String name){
        BarberShop shop = service.getByName(name);
        if(shop == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok().entity(shop).build();
    }

    @POST
    @Transactional
    public Response post(BarberShop payload){
        BarberShop shop = service.addBarberShop(payload);
        if(shop == null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.ok().entity(shop).build();
    }

    @DELETE
    @Transactional
    @Path("/{name}")
    public Response delete(@PathParam("name") String name){
        long ctn = service.removeBarbershop(name);

        if(ctn != 1)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().build();
    }

}
