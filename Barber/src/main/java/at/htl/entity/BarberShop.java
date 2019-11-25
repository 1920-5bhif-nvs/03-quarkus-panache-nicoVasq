package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.cfg.NotYetImplementedException;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Entity
public class BarberShop extends PanacheEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonbTransient @OneToMany(mappedBy = "barberShop",cascade = CascadeType.ALL)
    private List<Employee> employees;
    @JsonbTransient @OneToMany(cascade = CascadeType.ALL)
    private List<Equipment> equipment;

    //region Constructor
    public BarberShop() {
    }

    public BarberShop(String name) {
        this.name = name;
        employees = new LinkedList<>();
        equipment = new LinkedList<>();
    }
    //endregion

    //region Entity methods
    public static List<BarberShop> findAllBarbershops(){
        return BarberShop.findAll().list();
    }

    public static BarberShop findByName(String name){
        return BarberShop.find("name", name).firstResult();
    }

    @Transactional
    public static BarberShop createBarbershop(BarberShop shop){
        shop.persist();
        return shop;
    }

    @Transactional
    public static BarberShop updateBarbershop(BarberShop updatedShop){
        throw new NotYetImplementedException();
    }

    @Transactional
    public static long deleteBarbershop(String name){
        return delete("name",name);
    }
    //endregion

    //region Getter Setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }
    //endregion

    //region Methods
    public void AddEmployee(Employee emp){
        employees.add(emp);
        emp.setBarberShop(this);
    }
    public void AddEquipment(Equipment eq){
        equipment.add(eq);
    }
    //endregion
}
