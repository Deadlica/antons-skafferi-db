package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import rest.entities.Review;

import java.util.List;

@Named
@Transactional
public class ReviewBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Review> getAllReviews() {
        return em.createQuery("SELECT r FROM Review r", Review.class).getResultList();
    }

    public Review insertReview(Review review) {
        em.persist(review);
        return review;
    }
}
