package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Booking;
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

    @GET
    @Path("booking")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getOrdersFromBooking(@QueryParam("id") int bookingId) {
        return ordersBean.getOrdersFromBooking(bookingId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Orders insertOrder(Orders order) {
        return ordersBean.insertOrder(order);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteOrder(Orders order) {
        ordersBean.deleteOrder(order);
        return "ok";
    }
}