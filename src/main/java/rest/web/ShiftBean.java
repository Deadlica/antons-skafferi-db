package rest.web;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Expression;
import jakarta.transaction.Transactional;
import rest.entities.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

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

    public List<Shift> getAllLunchShifts() {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.beginTime = :beginTime", Shift.class);
        query.setParameter("beginTime", "11:00:00");
        return query.getResultList();
    }

    public List<Shift> getLunchShifts(String date) {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.date = :date AND s.beginTime = :beginTime", Shift.class);
        query.setParameter("date", date);
        query.setParameter("beginTime", "11:00:00");
        return query.getResultList();
    }

    public List<Shift> getAllDinnerShifts() {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.beginTime <> :beginTime", Shift.class);
        query.setParameter("beginTime", "11:00:00");
        return query.getResultList();
    }

    public List<Shift> getDinnerShifts(String date) {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.date = :date AND s.beginTime <> :beginTime", Shift.class);
        query.setParameter("date", date);
        query.setParameter("beginTime", "11:00:00");
        return query.getResultList();

    }

    public List<Shift> getUpcomingShifts(String id, String date) {
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.employee.ssn = :id AND s.date >= :date ORDER BY s.date", Shift.class);
        query.setParameter("id", id);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public Shift changeShiftEmployee(Long shiftId, String employeeId) {
        Shift shift = em.find(Shift.class, shiftId);
        WorkingEmployees employee = em.find(WorkingEmployees.class, employeeId);
        shift.setEmployee(employee);

        String date = shift.getDate();
        String time = shift.getBeginTime();
        TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r WHERE r.shift.date = :date AND r.shift.beginTime = :time AND r.toEmployee.ssn = :ssn", Request.class);
        query.setParameter("date", date);
        query.setParameter("time", time);
        query.setParameter("ssn", employeeId);

        List<Request> excessRequests = query.getResultList();
        for(Request r: excessRequests) {
            em.remove(r);
        }

        return shift;
    }

    public Shift insertShift(Shift shift) {
        em.persist(shift);
        return shift;
    }

    public void deleteShift(Shift shift) {
        TypedQuery<Request> deleteRequestsOfShift = em.createQuery("DELETE FROM Request r WHERE r.shift.id = :id", Request.class);
        deleteRequestsOfShift.setParameter("id", shift.getId());
        deleteRequestsOfShift.executeUpdate();
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.id = :id", Shift.class);
        query.setParameter("id", shift.getId());
        em.remove(query.getSingleResult());
    }

    public void deleteAllShiftsOfEmployee(Employee employee) {
        /*int query = em.createQuery("DELETE FROM Shift s WHERE s.employee.ssn = :ssn", Shift.class)
                .setParameter("ssn", employee.getSsn())
                .executeUpdate();*/
        TypedQuery<Shift> query = em.createQuery("SELECT s FROM Shift s WHERE s.employee.ssn = :ssn", Shift.class);
        query.setParameter("ssn", employee.getSsn());
        List<Shift> shifts = query.getResultList();
        for(Shift s: shifts) {
            em.remove(s);
        }
    }
}
