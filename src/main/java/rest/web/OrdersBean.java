package rest.web;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.QueryHint;
import jakarta.persistence.TypedQuery;
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

    public Orders insertOrder(Orders order) {
        Orders emptyOrder = new Orders();
        em.persist(emptyOrder);

        emptyOrder.setStatus(false);
        emptyOrder.setDish(order.getDish());
        emptyOrder.setBooking(order.getBooking());


        return order;
    }

    public void deleteOrder(Orders order) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.id = :id", Orders.class);
        query.setParameter("id", order.getId());
        em.remove(query.getSingleResult());
    }
}
