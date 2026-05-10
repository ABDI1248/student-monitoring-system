package com.school.studentstatusmonitoringsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "department_id")
    private Long departmentId;

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}