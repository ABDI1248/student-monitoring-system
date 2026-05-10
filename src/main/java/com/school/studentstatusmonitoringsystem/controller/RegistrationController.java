package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.model.Registration;
import com.school.studentstatusmonitoringsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // 1. Create registration (enroll student in course)
    @PostMapping
    public Registration create(@RequestBody Registration registration) {
        return registrationService.saveRegistration(registration);
    }

    // 2. Get all
    @GetMapping
    public List<Registration> getAll() {
        return registrationService.getAll();
    }

    // 3. Get by student
    @GetMapping("/student/{studentId}")
    public List<Registration> getByStudent(@PathVariable Long studentId) {
        return registrationService.getByStudent(studentId);
    }

    // 4. Get by course
    @GetMapping("/course/{courseId}")
    public List<Registration> getByCourse(@PathVariable Long courseId) {
        return registrationService.getByCourse(courseId);
    }

    // 5. Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        registrationService.delete(id);
    }
}