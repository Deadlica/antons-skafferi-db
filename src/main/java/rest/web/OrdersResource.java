package rest.web;

import jakarta.faces.annotation.HeaderValuesMap;
import jakarta.inject.Inject;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Order;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.CombinedOrders;
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
    public List<CombinedOrders> getOrdersFromBooking(@QueryParam("id") int bookingId) {
        return ordersBean.getOrdersFromBooking(bookingId);
    }

    @GET
    @Path("ready")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CombinedOrders> getReadyOrders() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        return ordersBean.getReadyOrders(simpleDateFormat.format(today));
    }

    @GET
    @Path("kitchen")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CombinedOrders> getKitchenOrders() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        return ordersBean.getKitchenOrders(simpleDateFormat.format(today));
    }

    @GET
    @Path("kitchen-and-ready")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CombinedOrders> getKitchenAndReadyOrders() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        return ordersBean.getAllNotServedOrders(simpleDateFormat.format(today));
    }

    @GET
    @Path("served")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orders> getServedOrders() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        return ordersBean.getServedOrders(simpleDateFormat.format(today));
    }

    @GET
    @Path("sales")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CombinedOrders> getMonthlyOrders(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        return ordersBean.getMonthlyOrders(startDate, endDate);
    }

    @PUT
    @Path("update-status-and-served")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Orders updateStatusAndServed(Orders order) {
        ordersBean.updateStatusAndServed(order);
        return order;
    }

    @PUT
    @Path("kitchen")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Orders changeStatus(Orders order) {
        ordersBean.changeStatus(order);
        return order;
    }

    @PUT
    @Path("served")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Orders changeServed(Orders order) {
        ordersBean.changeServed(order);
        return order;
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