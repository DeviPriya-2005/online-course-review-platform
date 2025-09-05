package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepo;

    public ReviewService(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getReviews(Long courseId) {
        return reviewRepo.findByCourseId(courseId);
    }

    public Review addOrUpdateReview(Review review) {
        Review existing = reviewRepo.findByCourseIdAndUser(review.getCourse().getId(), review.getUser());
        if (existing != null) {
            existing.setRating(review.getRating());
            existing.setComment(review.getComment());
            return reviewRepo.save(existing);
        }
        return reviewRepo.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepo.deleteById(id);
    }
}
