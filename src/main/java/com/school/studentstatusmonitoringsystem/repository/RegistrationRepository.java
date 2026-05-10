package com.school.studentstatusmonitoringsystem.repository;

import com.school.studentstatusmonitoringsystem.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByStudentId(Long studentId);

    List<Registration> findByCourseId(Long courseId);
}