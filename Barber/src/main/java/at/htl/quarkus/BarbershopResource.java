package at.htl.quarkus;

import at.htl.entity.BarberShop;

import javax.inject.Inject;
import javax.json.Json;
import javax.print.DocFlavor;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/barbershop")
public class BarbershopResource {

    @Inject
    BarbershopService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        List<BarberShop> shops = service.getAll();
        if(shops == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
         return  Response.ok().entity(shops).build();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name){
        BarberShop shop = service.getByName(name);
        if(shop == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok().entity(shop).build();
    }
}
