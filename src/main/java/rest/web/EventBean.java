package rest.web;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;
import rest.entities.Event;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@Named
@Transactional
public class EventBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Event> getAllEvents() {
        return em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    public List<Event> getUpcomingEvents(String date) {
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.date >= :date", Event.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public Event insertEvent(Event event) {
        em.persist(event);
        return event;
    }

    public void deleteEvent(Event event) {
        em.remove(em.find(Event.class, event.getId()));
    }

    public void deleteAllEvent() {
        List<Event> allEvents = em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        for(Event e : allEvents) {
            em.remove(e);
        }
    }

    public boolean uploadImage(byte[] imageBytes) throws IOException {
        try {
            File imageFile = new File("/home/samuel/IdeaProjects/antons-skafferi-db/src/main/resources/images/image.jpg");
            Files.write(imageFile.toPath(), imageBytes);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
