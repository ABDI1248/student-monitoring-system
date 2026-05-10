package com.school.studentstatusmonitoringsystem.repository;

import com.school.studentstatusmonitoringsystem.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    // 🔥 Find parent by phone
    List<Parent> findByPhone(String phone);

    // 🔥 Find parents by user role (useful later for security)
    @Query("SELECT p FROM Parent p WHERE p.user.role = :role")
    List<Parent> findByUserRole(@Param("role") String role);

    // 🔥 Find parent by username (VERY useful for login/dashboard)
    @Query("SELECT p FROM Parent p WHERE p.user.username = :username")
    Parent findByUsername(@Param("username") String username);

    // 🔥 Get all parents with at least one student (active parents)
    @Query("SELECT DISTINCT p FROM Parent p JOIN p.students s")
    List<Parent> findParentsWithStudents();
}