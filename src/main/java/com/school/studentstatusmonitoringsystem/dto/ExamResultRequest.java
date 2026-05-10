package com.school.studentstatusmonitoringsystem.dto;

import com.school.studentstatusmonitoringsystem.model.ExamType;
import java.time.LocalDate;

public class ExamResultRequest {

    private Long studentId;
    private Long courseId;
    private ExamType examType;
    private int score;
    private LocalDate date;

    // 🔹 GETTERS

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public ExamType getExamType() {
        return examType;
    }

    public int getScore() {
        return score;
    }

    public LocalDate getDate() {
        return date;
    }

    // 🔹 SETTERS

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}