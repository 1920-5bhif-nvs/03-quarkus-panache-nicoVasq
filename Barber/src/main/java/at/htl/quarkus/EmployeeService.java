package at.htl.quarkus;

import at.htl.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class EmployeeService {

    public List<Employee> findAll(){
        return Employee.findAllEmployees();
    }

}
