package com.school.studentstatusmonitoringsystem.repository;

import com.school.studentstatusmonitoringsystem.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

    List<ExamResult> findByStudentId(Long studentId);

    List<ExamResult> findByStudentIdAndCourseId(Long studentId, Long courseId);
}