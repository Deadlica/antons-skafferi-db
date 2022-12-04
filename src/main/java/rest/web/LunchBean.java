package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Lunch;

import java.util.List;

@Named
@Transactional
public class LunchBean {

    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Lunch> getAllLunch() {
        return em.createQuery("SELECT l FROM Lunch l JOIN FETCH l.dish ORDER BY l.date", Lunch.class).getResultList();
    }

    public List<Lunch> getLunchOfDate(String date) {
        TypedQuery<Lunch> query = em.createQuery("SELECT l FROM Lunch l JOIN FETCH l.dish WHERE l.date = :date", Lunch.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public Lunch insertLunch(Lunch lunch) {
        em.persist(lunch);
        return lunch;
    }

    public void deleteLunch(Lunch lunch) {
        Lunch lunchKey = em.createQuery("SELECT l FROM Lunch l WHERE l.date = :date AND l.dish.id = :id", Lunch.class)
                        .setParameter("date", lunch.getDate())
                        .setParameter("id", lunch.getDish().getId())
                        .getSingleResult();
        em.remove(lunchKey);
    }

    public void deleteAllLunchOfDish(int dishId) {
        int deletedCount = em.createQuery("DELETE FROM Lunch l WHERE l.dish.id = :id", Lunch.class)
                .setParameter("id", dishId)
                .executeUpdate();
    }
}
