package com.school.studentstatusmonitoringsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Department cannot be empty")
    private String department;

    @NotBlank(message = "Grade level cannot be empty")
    @Column(name = "grade_level")
    private String gradeLevel;

    @NotBlank(message = "Status cannot be empty")
    @Pattern(regexp = "Active|Inactive", message = "Status must be Active or Inactive")
    private String status;

    // 🔥 MANY-TO-MANY (back side)
    @ManyToMany(mappedBy = "students")
    @JsonIgnore   // 🚨 VERY IMPORTANT (prevents infinite loop)
    private List<Parent> parents;

    public Student() {
    }

    public Student(Long id, String name, String department, String gradeLevel, String status) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gradeLevel = gradeLevel;
        this.status = status;
    }

    // 🔹 GETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public String getStatus() {
        return status;
    }

    public List<Parent> getParents() {
        return parents;
    }

    // 🔹 SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
}