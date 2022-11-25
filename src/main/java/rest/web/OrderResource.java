package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Order;

import java.util.List;

@Path("/order")
public class OrderResource {
    @Inject
    OrderBean orderBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getALlOrders() {
        return orderBean.getAllOrders();
    }
}