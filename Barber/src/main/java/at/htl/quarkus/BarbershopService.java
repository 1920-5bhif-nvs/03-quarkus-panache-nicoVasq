package at.htl.quarkus;

import at.htl.entity.BarberShop;
import org.hibernate.cfg.NotYetImplementedException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BarbershopService {

    public List<BarberShop> getAll(){
        return BarberShop.findAllBarbershops();
    }

    public BarberShop getByName(String name) { return BarberShop.findByName(name); }

    public BarberShop addBarberShop(BarberShop shop) {
        return BarberShop.createBarbershop(shop);
    }
}
