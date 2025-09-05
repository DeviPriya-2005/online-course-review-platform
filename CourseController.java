package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCourseDetails(@PathVariable Long id) {
        Course course = service.getCourseDetails(id);
        double avgRating = service.getAverageRating(id);
        Map<String, Object> response = new HashMap<>();
        response.put("course", course);
        response.put("avgRating", avgRating);
        response.put("reviews", course != null ? course.getReviews() : List.of());
        return response;
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return service.addCourse(course);
    }
}
