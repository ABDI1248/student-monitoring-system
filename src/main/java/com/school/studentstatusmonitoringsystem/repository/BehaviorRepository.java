package com.school.studentstatusmonitoringsystem.repository;

import com.school.studentstatusmonitoringsystem.model.Behavior;
import com.school.studentstatusmonitoringsystem.model.BehaviorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BehaviorRepository extends JpaRepository<Behavior, Long> {

    List<Behavior> findByStudentId(Long studentId);

    List<Behavior> findByStudentIdAndBehaviorType(Long studentId, BehaviorType type);

    List<Behavior> findByDate(LocalDate date);
}