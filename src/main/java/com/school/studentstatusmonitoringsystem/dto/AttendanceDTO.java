package com.school.studentstatusmonitoringsystem.dto;

import java.time.LocalDate;

public class AttendanceDTO {

    private String studentName;
    private String courseName;
    private String status;
    private String period;
    private LocalDate date;

    public AttendanceDTO(String studentName,
                         String courseName,
                         String status,
                         String period,
                         LocalDate date) {

        this.studentName = studentName;
        this.courseName = courseName;
        this.status = status;
        this.period = period;
        this.date = date;
    }

    public String getStudentName() { return studentName; }
    public String getCourseName() { return courseName; }
    public String getStatus() { return status; }
    public String getPeriod() { return period; }
    public LocalDate getDate() { return date; }
}