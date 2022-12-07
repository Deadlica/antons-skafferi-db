package rest.web;

import jakarta.inject.Inject;
import jakarta.persistence.criteria.Order;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Booking;
import rest.entities.Orders;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GET
    @Path("ready")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getReadyOrders() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        return ordersBean.getReadyOrders(simpleDateFormat.format(today));
    }

    @GET
    @Path("kitchen")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getFoodOrders() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        return ordersBean.getFoodOrders("2022-12-17");
    }

    @GET
    @Path("sales")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getMonthlyOrders(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        return ordersBean.getMonthlyOrders(startDate, endDate);
    }

    @PUT
    @Path("kitchen")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String changeFoodStatus(Orders order) {
        ordersBean.changeFoodStatus(order);
        return "Updated";
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