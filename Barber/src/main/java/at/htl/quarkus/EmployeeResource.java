package at.htl.quarkus;

import at.htl.entity.Employee;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeService service;

    @GET
    public Response getAll(){
        List<Employee> emplist = service.findAll();
        if(emplist.size() == 0)
            return Response.status(404).build();

        return Response.status(200).entity(service.findAll()).build();
    }

    @GET
    @Path("/{name}")
    public Response getEmployeeByName(@PathParam("name") String name){
        Employee emp = service.findByName(name);
        if(emp == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok().entity(emp).build();
    }

    @POST
    @Transactional
    public Response post(Employee payload){
        service.createEmployee(payload);
        return Response.ok().build();
    }

    @PUT
    @Transactional
    public Response put(Employee payload){
        Employee emp = service.updateEmployee(payload);
        if(emp == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(emp).build();
    }

    @DELETE
    @Transactional
    @Path("/{name}")
    public Response delete(@PathParam("name") String name){
        long count = service.removeEmployee(name);
        if(count != 1)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().build();
    }
}
