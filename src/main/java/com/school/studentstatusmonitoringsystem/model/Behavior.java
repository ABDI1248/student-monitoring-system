package com.school.studentstatusmonitoringsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "behavior")
public class Behavior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 student
    @NotNull(message = "Student is required")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // 🔗 course (optional)
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // 🔥 ENUM (STRICT VALUES)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BehaviorType behaviorType;

    // description
    @Size(max = 255, message = "Description too long")
    private String description;

    private LocalDate date;

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public BehaviorType getBehaviorType() { return behaviorType; }
    public void setBehaviorType(BehaviorType behaviorType) { this.behaviorType = behaviorType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}