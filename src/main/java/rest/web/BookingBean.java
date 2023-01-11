package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Booking;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Named
@Transactional
public class BookingBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public BookingBean() {
        for(int tableNr = 1; tableNr <= totalTables; tableNr++) {
            usedTables.put(tableNr, false);
        }
    }

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

    public void removeBooking(Booking booking) {
        em.remove(em.find(Booking.class, booking.getId()));
    }

    public String insertBooking(Booking booking) {
        String response = "Bookings are full!";
        List<Booking> bookings = getBookingOfDate(booking.getDate());
        if(bookings.size() < totalTables) {
            int tableNr = booking.getTableNumber().intValue();

            for(Booking b : bookings) {
                usedTables.put(b.getTableNumber().intValue(), true);
            }
            if(usedTables.get(tableNr) == null || usedTables.get(tableNr)) {
                tableNr = getAvailableTable();
                booking.setTableNumber((long) tableNr);
            }
            em.persist(booking);
            response = "Booking has been made!";
        }
        return response;
    }

    private int getAvailableTable() {
        for(Map.Entry<Integer, Boolean> tableNr : usedTables.entrySet()) {
            if(!tableNr.getValue()) { //Table number is not occupied
                return tableNr.getKey();
            }
        }
        return 0;
    }

    private HashMap<Integer, Boolean> usedTables = new HashMap<Integer, Boolean>();
    private final int totalTables = 6;
}
