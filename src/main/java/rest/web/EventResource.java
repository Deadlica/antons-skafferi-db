package rest.web;

import jakarta.inject.Inject;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Event;

import java.util.List;

@Path("/event")
public class EventResource {
    @Inject
    EventBean eventBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        return eventBean.getAllEvents();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Event insertEvent(Event event) {
        return eventBean.insertEvent(event);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEvent(Event event) {
        eventBean.deleteEvent(event);
        return "ok";
    }
}