package com.school.studentstatusmonitoringsystem.dto;

public class CourseRequest {

    private String name;
    private Long teacherId;

    public String getName() {
        return name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}