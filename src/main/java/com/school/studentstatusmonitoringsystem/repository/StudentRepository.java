package com.school.studentstatusmonitoringsystem.repository;

import com.school.studentstatusmonitoringsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStatus(String status);

    List<Student> findByGradeLevel(String gradeLevel);

    List<Student> findByNameContainingIgnoreCase(String name);
    // 🔥 ADVANCED FILTER
    @Query("SELECT s FROM Student s WHERE " +
            "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:status IS NULL OR s.status = :status) AND " +
            "(:gradeLevel IS NULL OR s.gradeLevel = :gradeLevel)")
    List<Student> searchStudents(@Param("name") String name,
                                 @Param("status") String status,
                                 @Param("gradeLevel") String gradeLevel);
}