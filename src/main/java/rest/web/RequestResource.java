package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Request;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/request")
public class RequestResource {
    @Inject
    RequestBean requestBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Request> getAllRequests(@QueryParam("receiverId") String receiverId) {
        if(receiverId == null || receiverId.isEmpty()) {
            return requestBean.getAllRequests();
        }
        return requestBean.getRequest(receiverId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertRequest(RequestPlaceholder requestPlaceholder) {
        requestBean.insertRequest(requestPlaceholder.getSsn(), requestPlaceholder.getId());
        return "ok";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteRequest(RequestPlaceholder requestPlaceholder) {
        requestBean.deleteRequest(requestPlaceholder.getSsn(), requestPlaceholder.getId());
        return "ok";
    }

    public static class RequestPlaceholder {
        private String ssn;
        private int id;

        public String getSsn() {
            return ssn;
        }

        public void setSsn(String ssn) {
            this.ssn = ssn;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}