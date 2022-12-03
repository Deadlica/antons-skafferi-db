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
        return em.createQuery("SELECT c FROM Carte c", Carte.class).getResultList();
    }

    public Carte insertCarte(Carte carte) {
        em.persist(carte);
        return carte;
    }

    public void deleteCarte(int dishID) {
        TypedQuery<Carte> query = em.createQuery("SELECT c FROM Carte c WHERE c.dish.id = :id", Carte.class);
        query.setParameter("id", dishID);
        Carte carte = query.getSingleResult();
        em.remove(carte);
    }
}
