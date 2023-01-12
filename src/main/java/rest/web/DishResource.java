package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Dish;
import rest.entities.DishNotInCarte;
import rest.entities.DishView;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/dish")
public class DishResource {
    @Inject
    DishBean dishBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DishView> getAllDishes() {
        return dishBean.getAllDishes();
    }

    @GET
    @Path("available")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DishNotInCarte> getAvailableDishes() {
        return dishBean.getAvailableDishes();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Dish insertDish(Dish dish) {
        return dishBean.insertDish(dish);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDish(Dish dish) {
        dishBean.deleteDish(dish.getId());
        return "ok";
    }

}