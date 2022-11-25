package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import rest.entities.Order;

import java.util.List;

@Named
@Transactional
public class OrderBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Order> getAllOrders() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}
