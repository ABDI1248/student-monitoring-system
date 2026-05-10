package com.school.studentstatusmonitoringsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "exam_results")
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Many results → one student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // 🔗 Many results → one course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamType examType; // TEST / MID / FINAL

    @Min(value = 0, message = "Score must be >= 0")
    @Max(value = 100, message = "Score must be <= 100")
    private int score;

    private LocalDate date;

    public ExamResult() {}

    public ExamResult(Long id, Student student, Course course,
                      ExamType examType, int score, LocalDate date) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.examType = examType;
        this.score = score;
        this.date = date;
    }

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public ExamType getExamType() { return examType; }
    public void setExamType(ExamType examType) { this.examType = examType; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}