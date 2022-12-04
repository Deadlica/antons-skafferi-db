package rest.web;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Booking;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/booking")
public class BookingResource {
    @Inject
    BookingBean bookingBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getAllBookings(@QueryParam("date") String date, @QueryParam("phoneNumber") String phoneNumber) {
        if(date == null && phoneNumber == null) {
            return bookingBean.getAllBookings();
        }
        if(date != null && phoneNumber == null) {
            return bookingBean.getBookingOfDate(date);
        }
        if(date == null && phoneNumber != null) {
            return bookingBean.getAllBookingOfPhoneNumber(phoneNumber);
        }
        return bookingBean.getBooking(date, phoneNumber);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("count")
    public String countBookingsOfDate(@QueryParam("date") String date) {
        int size = bookingBean.getBookingOfDate(date).size();
        return "{\"size\":" + size + "}";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String removeBooking(Booking booking) {
        bookingBean.removeBooking(booking);
        return "ok";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Booking insertBooking(Booking booking) {
        return bookingBean.insertBooking(booking);
    }

}