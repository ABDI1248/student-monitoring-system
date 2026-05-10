package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.dto.BehaviorRequest;
import com.school.studentstatusmonitoringsystem.model.*;
import com.school.studentstatusmonitoringsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BehaviorService {

    @Autowired
    private BehaviorRepository behaviorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ParentRepository parentRepository;

    // =========================
    // 🔥 SAVE (SECURED)
    // =========================
    public Behavior save(BehaviorRequest request, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Teacher teacher = teacherRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = null;

        // course is optional
        if (request.getCourseId() != null) {

            course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            // 🔐 SECURITY CHECK
            if (!course.getTeacher().getId().equals(teacher.getId())) {
                throw new RuntimeException("You are not allowed to add behavior for this course");
            }
        }

        Behavior behavior = new Behavior();
        behavior.setStudent(student);
        behavior.setCourse(course);
        behavior.setBehaviorType(request.getBehaviorType());
        behavior.setDescription(request.getDescription());
        behavior.setDate(request.getDate());

        return behaviorRepository.save(behavior);
    }

    // =========================
    // BASIC
    // =========================

    public List<Behavior> getAll() {
        return behaviorRepository.findAll();
    }

    public List<Behavior> getByStudent(Long studentId) {
        return behaviorRepository.findByStudentId(studentId);
    }

    public List<Behavior> getByType(Long studentId, BehaviorType type) {
        return behaviorRepository.findByStudentIdAndBehaviorType(studentId, type);
    }

    public List<Behavior> getByDate(LocalDate date) {
        return behaviorRepository.findByDate(date);
    }

    public void delete(Long id) {
        behaviorRepository.deleteById(id);
    }

    // =========================
    // 👨‍👩‍👧 PARENT VIEW (SECURE)
    // =========================

    public List<Behavior> getBehaviorForParent(String username) {

        Parent parent = parentRepository.findByUsername(username);

        if (parent == null) {
            throw new RuntimeException("Parent not found");
        }

        List<Student> students = parent.getStudents();

        List<Behavior> result = new ArrayList<>();

        for (Student student : students) {
            result.addAll(behaviorRepository.findByStudentId(student.getId()));
        }

        return result;
    }
}