package com.school.studentstatusmonitoringsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many registrations → one student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Many registrations → one course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private String semester;

    public Registration() {
    }

    public Registration(Long id, Student student, Course course, String semester) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}