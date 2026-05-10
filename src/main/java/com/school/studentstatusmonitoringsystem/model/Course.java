package com.school.studentstatusmonitoringsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name cannot be empty")
    private String name;

    // 🔥 RELATION TO USER (TEACHER)
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Course() {}

    public Course(Long id, String name, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}