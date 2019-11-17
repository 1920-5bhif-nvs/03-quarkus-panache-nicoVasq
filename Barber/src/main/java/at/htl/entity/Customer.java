package at.htl.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Customer extends Person{

    private LocalDate lastVisited;

    //region Constructor
    public Customer() {
    }

    public Customer(String name, LocalDate lastVisited) {
        super(name);
        this.lastVisited = lastVisited;
    }
    //endregion

    //region Entity methods
    public static List<Customer> findAllCustomers(){
        return Customer.findAll().list();
    }

    public static Customer findByName(String name){
        return find("name", name).firstResult();
    }
    //endregion

    //region Getter Setter
    public LocalDate getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(LocalDate lastVisited) {
        this.lastVisited = lastVisited;
    }
    //endregion
}
