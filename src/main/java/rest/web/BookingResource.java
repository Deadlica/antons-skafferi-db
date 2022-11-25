package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Booking;

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("remove")
    public Booking removeBooking(Booking booking) {
        return bookingBean.removeBooking(booking);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Booking insertBooking(Booking booking) {
        return bookingBean.insertBooking(booking);
    }
}