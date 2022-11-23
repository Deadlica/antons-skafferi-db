package rest.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import rest.entities.Review;

import java.util.List;

@Path("/review")
public class ReviewResource {
    @Inject
    ReviewBean reviewBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getAllReviews() {
        return reviewBean.getAllReviews();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Review insertReview(Review review) {
        return reviewBean.insertReview(review);
    }
}