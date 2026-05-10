package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.dto.CourseRequest;
import com.school.studentstatusmonitoringsystem.model.Course;
import com.school.studentstatusmonitoringsystem.model.Teacher;
import com.school.studentstatusmonitoringsystem.repository.CourseRepository;
import com.school.studentstatusmonitoringsystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    // 1. Create course
    public Course saveCourse(CourseRequest request) {

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = new Course();
        course.setName(request.getName());
        course.setTeacher(teacher);

        return courseRepository.save(course);
    }

    // 2. Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // 3. Get by id
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    // 4. Delete
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // 5. Search by name
    public List<Course> searchByName(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name);
    }

    // 6. By teacher
    public List<Course> getByTeacher(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }
}