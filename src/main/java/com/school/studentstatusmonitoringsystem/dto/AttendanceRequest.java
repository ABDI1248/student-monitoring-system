package com.school.studentstatusmonitoringsystem.dto;

import java.time.LocalDate;

public class AttendanceRequest {

    private Long studentId;
    private Long courseId;
    private String status;
    private String period;
    private LocalDate attendanceDate;

    // ===== GETTERS =====

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getStatus() {
        return status;
    }

    public String getPeriod() {
        return period;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    // ===== SETTERS =====

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
}