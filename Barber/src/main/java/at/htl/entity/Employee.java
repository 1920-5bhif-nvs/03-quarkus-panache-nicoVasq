package at.htl.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Employee extends Person{

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonbTransient
    private List<Service> serviceList;
    @ManyToOne
    private BarberShop barberShop;
    private double salary;

    //region Constructor
    public Employee() {
    }

    public Employee(String name, double salary, BarberShop shop)
    {
        super(name);
        this.salary = salary;
        serviceList = new LinkedList<>();
        barberShop = shop;
        barberShop.AddEmployee(this);
    }
    //endregion

    //region Entity methods
    public static List<Employee> findAllEmployees(){
        return Employee.findAll().list();
    }

    public static Employee findByName(String name) {
        return find("name", name).firstResult();
    }
    //endregion

    //region Setter Getter
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public BarberShop getBarberShop() {
        return barberShop;
    }

    public void setBarberShop(BarberShop barberShop) {
        this.barberShop = barberShop;
    }
    //endregion

    public void AddServiceToList(Service s){
        serviceList.add(s);
    }
}
