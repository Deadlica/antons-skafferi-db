package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import rest.entities.Dish;

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
}
