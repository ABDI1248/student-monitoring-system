package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.dto.CourseRequest;
import com.school.studentstatusmonitoringsystem.model.Course;
import com.school.studentstatusmonitoringsystem.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 1. Create course
    @PostMapping
    public Course createCourse(@RequestBody CourseRequest request) {
        return courseService.saveCourse(request);
    }

    // 2. Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // 3. Get by id
    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // 4. Delete course
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    // 5. Search by name
    @GetMapping("/search")
    public List<Course> search(@RequestParam String name) {
        return courseService.searchByName(name);
    }

    // 6. By teacher
    @GetMapping("/teacher/{teacherId}")
    public List<Course> getByTeacher(@PathVariable Long teacherId) {
        return courseService.getByTeacher(teacherId);
    }
}