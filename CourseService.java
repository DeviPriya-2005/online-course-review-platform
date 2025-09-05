package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Review;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepo;
    private final ReviewRepository reviewRepo;

    public CourseService(CourseRepository courseRepo, ReviewRepository reviewRepo) {
        this.courseRepo = courseRepo;
        this.reviewRepo = reviewRepo;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseDetails(Long id) {
        return courseRepo.findById(id).orElse(null);
    }

    public double getAverageRating(Long courseId) {
        List<Review> reviews = reviewRepo.findByCourseId(courseId);
        if (reviews.isEmpty()) return 0;
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0);
    }

    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }
}
