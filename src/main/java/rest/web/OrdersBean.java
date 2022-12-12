package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.CombinedOrders;
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

    public List<CombinedOrders> getOrdersFromBooking(int bookingId) {
        TypedQuery<CombinedOrders> query = em.createQuery("SELECT c FROM CombinedOrders c WHERE c.booking.id = :bookingId", CombinedOrders.class);
        query.setParameter("bookingId", bookingId);
        return query.getResultList();
    }

    public List<Orders> getReadyOrders(String date) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.booking.date = :date AND o.status = :status AND o.served = :served", Orders.class);
        query.setParameter("date", date);
        query.setParameter("status", true);
        query.setParameter("served", false);
        return query.getResultList();
    }

    public List<CombinedOrders> getFoodOrders(String date) {
        TypedQuery<CombinedOrders> query = em.createQuery("SELECT c FROM CombinedOrders c WHERE c.category <> :category AND c.status = :status AND c.booking.date = :date", CombinedOrders.class);
        query.setParameter("category", "Dryck");
        query.setParameter("status", false);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Orders> getServedOrders(String date) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.booking.date = :date AND o.status = :status AND o.served = :served", Orders.class);
        query.setParameter("date", date);
        query.setParameter("status", true);
        query.setParameter("served", true);
        return query.getResultList();
    }

    public List<Orders> getMonthlyOrders(String startDate, String endDate) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.booking.date >= :startDate AND o.booking.date <= :endDate", Orders.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    public void changeFoodStatus(Orders order) {
        Orders foodItem = em.find(Orders.class, order.getId());
        em.createQuery("UPDATE Orders o SET o.status = :status WHERE o.id = :id", Orders.class)
                .setParameter("status", !foodItem.getStatus())
                .setParameter("id", order.getId())
                .executeUpdate();
    }

    public void markOrderAsServed(Orders order) {
        Orders foodItem = em.find(Orders.class, order.getId());
        em.createQuery("UPDATE Orders o SET o.served = :served WHERE o.id = :id", Orders.class)
                .setParameter("served", true)
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
