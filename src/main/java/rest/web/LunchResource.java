package rest.web;

import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.annotation.RequestParameterValuesMap;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Lunch;

import javax.print.attribute.standard.Media;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;

@Path("/lunch")
public class LunchResource {
    @Inject
    LunchBean lunchBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lunch> getAllLunch(@QueryParam("date") String date) {
        if(date == null) {
            return lunchBean.getAllLunch();
        }
        return lunchBean.getLunchOfDate(date);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("today")
    public List<Lunch> getTodayLunch() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return lunchBean.getLunchOfDate(simpleDateFormat.format(date));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Lunch insertLunch(Lunch lunch) {
        return lunchBean.insertLunch(lunch);
    }
}