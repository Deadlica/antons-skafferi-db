package rest.web;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Event;

import java.util.List;

@Named
@Transactional
public class EventBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Event> getAllEvents() {
        return em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    public Event insertEvent(Event event) {
        em.persist(event);
        return event;
    }

    public void deleteEvent(Event event) {
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.id = :id", Event.class);
        query.setParameter("id", event.getId());
        em.remove(query.getSingleResult());
    }
}
