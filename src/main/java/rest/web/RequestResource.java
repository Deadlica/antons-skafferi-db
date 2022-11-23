package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Request;

import java.util.List;

@Path("/request")
public class RequestResource {
    @Inject
    RequestBean requestBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Request> getAllRequests(@QueryParam("receiverId") String receiverId) {
        if(receiverId == null) {
            return requestBean.getAllRequests();
        }
        return requestBean.getRequest(receiverId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Request insertRequest(Request request) {
        return requestBean.insertRequest(request);
    }
}