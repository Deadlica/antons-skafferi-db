package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.QueryParam;
import rest.entities.Request;

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
        TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r WHERE r.toEmployee.ssn = :employeeId", Request.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }

    public String deleteRequest(Long shiftId) {
        return em.find(Request.class, shiftId).toString();
    }

    public Request insertRequest(Request request) {
        em.persist(request);
        return request;
    }
}
