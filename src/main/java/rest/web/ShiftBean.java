package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Employee;
import rest.entities.Request;
import rest.entities.Shift;

import java.lang.reflect.Type;
import java.util.List;

@Named
@Transactional
public class ShiftBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Shift> getAllShifts() {
        return em.createQuery("SELECT s FROM Shift s JOIN FETCH s.employee", Shift.class).getResultList();
    }

    public List<Shift> getShiftsOfEmployee(String employeeID) {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.employee.ssn = :employeeID", Shift.class);
        query.setParameter("employeeID", employeeID);
        return query.getResultList();
    }

    public List<Shift> getShift(String date) {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.date = :date ORDER BY s.beginTime, s.employee.firstName", Shift.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Shift> getShift(Integer id) {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.id = :id", Shift.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Shift> getShift(Integer id, String employeeId) {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.id = :id AND s.employee.ssn = :employeeId", Shift.class);
        query.setParameter("id", id);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }

    public List<Shift> getUpcomingShifts(String id, String date) {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.employee.ssn = :id AND s.date >= :date ORDER BY s.date", Shift.class);
        query.setParameter("id", id);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public Shift changeShiftEmployee(Long shiftId, String employeeId) {
        TypedQuery<Shift> query = em.createQuery("UPDATE Shift s SET s.employee.ssn = :ssn WHERE s.id = :shiftId", Shift.class);
        query.setParameter("ssn", employeeId);
        query.setParameter("shiftId", shiftId);

        TypedQuery<Request> deleteRemainingRequestsOfShift = em.createQuery("DELETE FROM Request r WHERE r.shift.id = :shiftId", Request.class);
        deleteRemainingRequestsOfShift.setParameter("shiftId", shiftId);
        deleteRemainingRequestsOfShift.getResultList();

        return query.getSingleResult();
    }

    public Shift insertShift(Shift shift) {
        em.persist(shift);
        return shift;
    }
}
