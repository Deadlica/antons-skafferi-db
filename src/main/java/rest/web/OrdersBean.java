package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import rest.entities.Orders;

import java.util.List;

@Named
@Transactional
public class OrdersBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Orders> getAllOrders() {
        return em.createQuery("SELECT o FROM Orders o", Orders.class).getResultList();
    }
}
