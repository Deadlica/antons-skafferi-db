package rest.web;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import rest.entities.*;

import java.util.List;

@Named
@Transactional
public class DishBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<DishView> getAllDishes() {
        return em.createQuery("SELECT d FROM DishView d ORDER BY d.name", DishView.class).getResultList();
    }

    public List<DishNotInCarte> getAvailableDishes() {
        return em.createQuery("SELECT d FROM DishNotInCarte d ORDER BY d.type, d.name", DishNotInCarte.class).getResultList();
    }

    public Dish insertDish(Dish dish) {
        if(dishExists(dish.getName())) {
            String type = dish.getType();
            TypedQuery<Dish> query = em.createQuery("SELECT d FROM Dish d WHERE d.name = :name", Dish.class);
            query.setParameter("name", dish.getName());
            dish = query.getSingleResult();
            dish.setDeleted(false);
            dish.setType(type);

        }
        else {
            dish.setDeleted(false);
            em.persist(dish);
        }
        return dish;
    }

    @Inject
    CarteBean carteBean;
    @Inject
    LunchBean lunchBean;
    public void deleteDish(int id) {
        lunchBean.deleteAllLunchOfDish(id);

        Dish dish = em.createQuery("SELECT d FROM Dish d WHERE d.id = :id", Dish.class).setParameter("id", id).getSingleResult();

        if(dishExistInOrder(id)) { //Dish can't be deleted, so it hides it in the view
            dish.setDeleted(true);
        }
        else { //No order with dish, ok to be deleted
            carteBean.deleteCarte(id);
            em.remove(dish);
        }
    }

    private boolean dishExistInOrder(int id) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.dish.id = :id", Orders.class);
        query.setParameter("id", id);
        if(query.getResultList().isEmpty()) { //Dish is ok to be deleted
            return false;
        }
        return true; //Hides dish
    }

    private boolean dishExists(String dishName) {
        TypedQuery<Orders> query = em.createQuery("SELECT o FROM Orders o WHERE o.dish.name = :dishName", Orders.class);
        query.setParameter("dishName", dishName);
        if(query.getResultList().isEmpty()) {
            return false;
        }
        return true;
    }
}
