package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import rest.entities.Employee;
import rest.entities.Request;
import rest.entities.Shift;
import rest.entities.WorkingEmployees;

import java.util.List;

@Named
@Transactional
public class RequestBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Request> getAllRequests() {
        return em.createQuery("SELECT r FROM Request r JOIN FETCH r.shift", Request.class).getResultList();
    }

    public List<Request> getRequest(String employeeId) {
        TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r WHERE r.toEmployee.ssn = :employeeId ORDER BY r.shift.date", Request.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }

    public void deleteRequests(Long shiftId) {
        TypedQuery<Request> requests = em.createQuery("SELECT r FROM Request r WHERE r.shift.id = :shiftId", Request.class);
        requests.setParameter("shiftId", shiftId);
        List<Request> ssn = requests.getResultList();

        for(Request r: ssn) {
            em.remove(r);
        }
    }

    public void deleteRequest(String ssn, int id) {
        TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r WHERE r.shift.id = :id AND r.toEmployee.ssn = :ssn", Request.class);
        query.setParameter("id", id);
        query.setParameter("ssn", ssn);
        Request request = query.getSingleResult();

        em.remove(request);
    }

    public void insertRequest(String ssn, int id) {
        WorkingEmployees employee = em.createQuery("SELECT w FROM WorkingEmployees w WHERE w.ssn = :ssn", WorkingEmployees.class).setParameter("ssn", ssn).getSingleResult();
        Shift shift = em.createQuery("SELECT s FROM Shift s WHERE s.id = :id", Shift.class).setParameter("id", id).getSingleResult();
        Request request = new Request();
        request.setToEmployee(employee);
        request.setShift(shift);


        em.persist(request);
    }

    public void deleteRequestsOfEmployee(Employee employee) {
        int deleteCount = em.createQuery("DELETE FROM Request r WHERE r.shift.employee.ssn = :ssn OR r.toEmployee.ssn = :ssn", Request.class)
                .setParameter("ssn", employee.getSsn())
                .executeUpdate();
    }
}
