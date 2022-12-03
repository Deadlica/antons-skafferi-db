package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Carte;
import rest.entities.Dish;
import rest.entities.Lunch;

import java.util.List;

@Named
@Transactional
public class DishBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Dish> getAllDishes() {
        return em.createQuery("SELECT d FROM Dish d ORDER BY d.name", Dish.class).getResultList();
    }

    public Dish insertDish(Dish dish) {
        em.persist(dish);
        return dish;
    }

    public void deleteDish(int id) {
        //em.remove(em.find(Dish.class, id));
        /*TypedQuery<Dish> query = em.createQuery("SELECT d FROM Dish d WHERE d.id = :id", Dish.class);
        query.setParameter("id", id);
        em.remove(query.getSingleResult());*/
        Carte carteItem = em.find(Carte.class, id);
        Lunch lunchItem = em.find(Lunch.class, id);
        Dish dishItem = em.find(Dish.class, id);
        em.remove(carteItem);
        em.remove(lunchItem);
        em.remove(dishItem);

    }
}
