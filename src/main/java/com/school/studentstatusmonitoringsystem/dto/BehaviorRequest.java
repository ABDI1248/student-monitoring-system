package com.school.studentstatusmonitoringsystem.dto;

import com.school.studentstatusmonitoringsystem.model.BehaviorType;

import java.time.LocalDate;

public class BehaviorRequest {

    private Long studentId;
    private Long courseId;
    private BehaviorType behaviorType;
    private String description;
    private LocalDate date;

    // GETTERS

    public Long getStudentId() { return studentId; }
    public Long getCourseId() { return courseId; }
    public BehaviorType getBehaviorType() { return behaviorType; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }

    // SETTERS

    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public void setBehaviorType(BehaviorType behaviorType) { this.behaviorType = behaviorType; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(LocalDate date) { this.date = date; }
}