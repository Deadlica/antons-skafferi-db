package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Carte;

import java.util.List;

@Named
@Transactional
public class CarteBean {

    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Carte> getAllCarte() {
        return em.createQuery("SELECT c FROM Carte c ORDER BY c.category, c.dish.type, c.dish.name", Carte.class).getResultList();
    }

    public Carte insertCarte(Carte carte) {
        em.persist(carte);
        return carte;
    }

    public void deleteCarte(int dishId) {
        int deleteCount = em.createQuery("DELETE FROM Carte c WHERE c.dish.id = :id", Carte.class)
                .setParameter("id", dishId)
                .executeUpdate();
    }
}
