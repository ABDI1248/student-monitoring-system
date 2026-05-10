package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.model.Registration;
import com.school.studentstatusmonitoringsystem.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    // Create registration
    public Registration saveRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    // Get all
    public List<Registration> getAll() {
        return registrationRepository.findAll();
    }

    // Get by student
    public List<Registration> getByStudent(Long studentId) {
        return registrationRepository.findByStudentId(studentId);
    }

    // Get by course
    public List<Registration> getByCourse(Long courseId) {
        return registrationRepository.findByCourseId(courseId);
    }

    // Delete
    public void delete(Long id) {
        registrationRepository.deleteById(id);
    }
}