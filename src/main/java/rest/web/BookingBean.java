package rest.web;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Booking;
import rest.entities.Employee;

import java.awt.print.Book;
import java.lang.reflect.Type;
import java.util.List;

@Named
@Transactional
public class BookingBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Booking> getAllBookings() {
        return em.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
    }

    public List<Booking> getBookingOfDate(String date) {
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.date = :date", Booking.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Booking> getAllBookingOfPhoneNumber(String phoneNumber) {
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.phoneNumber = :phoneNumber", Booking.class);
        query.setParameter("phoneNumber", phoneNumber);
        return query.getResultList();
    }

    public List<Booking> getBooking(String date, String phoneNumber) {
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.date = :date AND b.phoneNumber = :phoneNumber", Booking.class);
        query.setParameter("date", date);
        query.setParameter("phoneNumber", phoneNumber);
        return query.getResultList();
    }

    public Booking removeBooking(Booking booking) {
        em.remove(booking);
        return booking;
    }

    public Booking insertBooking(Booking booking) {
        em.persist(booking);
        return booking;
    }
}
