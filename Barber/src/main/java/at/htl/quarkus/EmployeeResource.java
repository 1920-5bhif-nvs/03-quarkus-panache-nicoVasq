package at.htl.quarkus;

import at.htl.entity.Employee;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
public class EmployeeResource {

    @Inject
    EmployeeService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Employee> emplist = service.findAll();
        if(emplist.size() == 0)
            return Response.status(404).build();

        return Response.status(200).entity(service.findAll()).build();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByName(@PathParam("name") String name){
        Employee emp = service.findByName(name);
        if(emp == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok().entity(emp).build();
    }
}
