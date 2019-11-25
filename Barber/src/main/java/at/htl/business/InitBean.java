package at.htl.business;

import at.htl.entity.*;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class InitBean {

    @Transactional
    void init(@Observes StartupEvent ev){
        System.out.println("-----INIT-----");

        loadData();
    }

    @Transactional
    private void loadData(){

        BarberShop shop = new BarberShop("Klipp");
        BarberShop shop2 = new BarberShop("DM");
        BarberShop shop3 = new BarberShop("Schnittzone");
        shop.persist();
        shop2.persist();
        shop3.persist();


        Blowdryer blowdryer = new Blowdryer("Maxwell",7 , 3, true);
        blowdryer.persist();

        Haircolor haircolor = new Haircolor("Schwarzkopf", 5 ,"Ultraviolet", true);
        Haircolor haircolor2 = new Haircolor("Loreal", 20 ,"Red", true);
        Haircolor haircolor3 = new Haircolor("Syoss", 12 ,"Blue", true);
        haircolor.persist();
        haircolor2.persist();
        haircolor3.persist();

        shop.AddEquipment(blowdryer);
        shop.AddEquipment(haircolor);
        shop.AddEquipment(haircolor2);
        shop.AddEquipment(haircolor3);

        Employee employee = new Employee("Sophie",1200, shop);
        Employee employee2 = new Employee("Dianna",1205, shop);
        employee.persist();
        employee2.persist();

        Customer customer = new Customer("Tom", LocalDate.of(2018, Month.JUNE, 6));
        Customer customer2 = new Customer("Max", LocalDate.of(2018, Month.JUNE, 23));
        customer.persist();
        customer2.persist();

        Service service = new Service(customer, employee, "Cut Hair",20, customer.getLastVisited());
        Service service2 = new Service(customer2, employee2, "Dye Hair", 50, customer2.getLastVisited());
        service.persist();
        service2.persist();

    }
}
