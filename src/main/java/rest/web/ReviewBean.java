package rest.web;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import rest.entities.Review;

import java.lang.reflect.Type;
import java.util.List;

@Named
@Transactional
public class ReviewBean {
    @PersistenceContext(unitName = "default")
    EntityManager em;

    public List<Review> getAllReviews() {
        return em.createQuery("SELECT r FROM Review r ORDER BY r.ranking DESC", Review.class).getResultList();
    }

    public Review insertReview(Review review) {
        em.persist(review);
        return review;
    }

    public void deleteReview(Review review) {
        Review r = em.find(Review.class, review.getId());
        em.remove(r);
    }

    public void deleteAllReview() {
        List<Review> reviews = em.createQuery("SELECT r FROM Review r", Review.class).getResultList();
        for(Review r : reviews) {
            em.remove(r);
        }
    }
}
