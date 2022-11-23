package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
}
