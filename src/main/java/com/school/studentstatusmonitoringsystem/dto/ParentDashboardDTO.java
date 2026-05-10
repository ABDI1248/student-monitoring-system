package com.school.studentstatusmonitoringsystem.dto;

import java.util.List;

public class ParentDashboardDTO {

    private String studentName;

    private List<AttendanceDTO> attendance;
    private List<ExamSummaryDTO> examResults;
    private List<?> behavior;

    // GETTERS & SETTERS

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<AttendanceDTO> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<AttendanceDTO> attendance) {
        this.attendance = attendance;
    }

    public List<ExamSummaryDTO> getExamResults() {
        return examResults;
    }

    public void setExamResults(List<ExamSummaryDTO> examResults) {
        this.examResults = examResults;
    }

    public List<?> getBehavior() {
        return behavior;
    }

    public void setBehavior(List<?> behavior) {
        this.behavior = behavior;
    }
}