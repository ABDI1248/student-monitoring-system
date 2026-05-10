package com.school.studentstatusmonitoringsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "parents")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 Link to User table (user_id column)
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ✅ Validation (this field exists in DB)
    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    // 🔥 Many-to-Many with Student (via parent_student table)
    @ManyToMany
    @JoinTable(
            name = "parent_student",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    // 🔹 Default constructor
    public Parent() {
    }

    // 🔹 Constructor
    public Parent(Long id, User user, String phone, List<Student> students) {
        this.id = id;
        this.user = user;
        this.phone = phone;
        this.students = students;
    }

    // 🔹 GETTERS

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPhone() {
        return phone;
    }

    public List<Student> getStudents() {
        return students;
    }

    // 🔹 SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}