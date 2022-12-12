package rest.web;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Carte;
import rest.entities.Dish;
import rest.entities.DishNotInCarte;
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

    public List<DishNotInCarte> getAvailableDishes() {
        return em.createQuery("SELECT d FROM DishNotInCarte d", DishNotInCarte.class).getResultList();
    }

    public Dish insertDish(Dish dish) {
        em.persist(dish);
        return dish;
    }

    @Inject
    CarteBean carteBean;
    @Inject
    LunchBean lunchBean;
    public void deleteDish(int id) {
        carteBean.deleteCarte(id);
        lunchBean.deleteAllLunchOfDish(id);



        Dish dish = em.createQuery("SELECT d FROM Dish d WHERE d.id = :id", Dish.class).setParameter("id", id).getSingleResult();

        em.remove(dish);
    }
}
