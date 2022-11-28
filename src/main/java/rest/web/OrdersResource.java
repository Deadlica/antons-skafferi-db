package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Orders;

import java.util.List;

@Path("/orders")
public class OrdersResource {
    @Inject
    OrdersBean ordersBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getALlOrders() {
        return ordersBean.getAllOrders();
    }
}