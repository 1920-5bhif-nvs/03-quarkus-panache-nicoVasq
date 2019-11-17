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

    @Inject
    EntityManager entityManager;

    public List<BarberShop> getAll(){
       // TypedQuery<BarberShop> query = entityManager.createNamedQuery("BarberShop.findAll", BarberShop.class);
       // return query.getResultList();
        throw new NotYetImplementedException();
    }
}
