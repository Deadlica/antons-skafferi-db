package rest.web;

import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;
import rest.entities.Event;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Named
@Transactional
public class EventBean {
    private static final Path IMAGE_DIR = resolveImageDir();

    private static Path resolveImageDir() {
        String dir = System.getenv("EVENT_IMAGE_DIR");
        if (dir == null || dir.isBlank()) dir = "/var/skafferi/event-images";
        Path p = Paths.get(dir);
        try { Files.createDirectories(p); } catch (IOException ignored) {}
        return p;
    }

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

    public boolean uploadImage(byte[] imageBytes, String imageName) throws IOException {
        try {
            Files.write(IMAGE_DIR.resolve(imageName), imageBytes);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public byte[] getImage(String fileName) throws IOException {
        Path file = IMAGE_DIR.resolve(fileName);
        if (!Files.exists(file)) return new byte[0];
        return Files.readAllBytes(file);
    }

    public boolean removeImage(String fileName) {
        try {
            return Files.deleteIfExists(IMAGE_DIR.resolve(fileName));
        } catch (IOException e) {
            return false;
        }
    }
}
