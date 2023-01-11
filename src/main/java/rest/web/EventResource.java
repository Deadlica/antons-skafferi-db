package rest.web;

import jakarta.inject.Inject;
import jakarta.servlet.http.Part;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Event;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("upcoming")
    public List<Event> getUpcomingEvents() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return eventBean.getUpcomingEvents(simpleDateFormat.format(date));
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

    @PUT
    @Path("delete-all")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAllEvent() {
        eventBean.deleteAllEvent();
        return "ok";
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_PLAIN})
    public String uploadImage(ImageContent imageContent) throws IOException {
        if(eventBean.uploadImage(imageContent.getImageBytes(), imageContent.getImageName())) {
            return imageContent.getImageName();
        }
        return "Failed to upload image";
    }

    @GET
    @Path("image")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getImage() throws IOException {
        return eventBean.getImage();
    }

    public static class ImageContent {
        private byte[] imageBytes;
        private String imageName;

        public byte[] getImageBytes() {
            return imageBytes;
        }

        public void setImageBytes(byte[] imageBytes) {
            this.imageBytes = imageBytes;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }
    }
}