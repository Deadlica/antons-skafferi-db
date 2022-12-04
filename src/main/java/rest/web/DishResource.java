package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Dish;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/dish")
public class DishResource {
    @Inject
    DishBean dishBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dish> getAllDishes() {
        return dishBean.getAllDishes();
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