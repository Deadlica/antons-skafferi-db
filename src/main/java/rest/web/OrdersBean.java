package rest.web;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.QueryHint;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Order;
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

    public List<Orders> getOrdersFromBooking(int bookingId) {
        TypedQuery<Orders> query = em.createQuery("SELECT o, c FROM Orders o JOIN Carte c ON o.dish.id = c.dish.id WHERE o.booking.id = :bookingId", Orders.class);
        query.setParameter("bookingId", bookingId);
        return query.getResultList();
    }

    public List<Orders> getReadyOrders(String date) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.booking.date = :date AND o.status = :status", Orders.class);
        query.setParameter("date", date);
        query.setParameter("status", true);
        return query.getResultList();
    }

    public List<Orders> getFoodOrders(String date) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o JOIN Carte c ON o.dish.id = c.dish.id WHERE c.category <> :category AND o.status = :status AND o.booking.date = :date", Orders.class);
        query.setParameter("category", "Dryck");
        query.setParameter("status", false);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public void changeFoodStatus(Orders order) {
        Orders foodItem = em.find(Orders.class, order.getId());
        em.createQuery("UPDATE Orders o SET o.status = :status WHERE o.id = :id", Orders.class)
                .setParameter("status", !foodItem.getStatus())
                .setParameter("id", order.getId())
                .executeUpdate();
    }

    public Orders insertOrder(Orders order) {
        em.persist(order);
        return order;
    }

    public void deleteOrder(Orders order) {
        em.remove(em.find(Orders.class, order.getId()));
    }
}
