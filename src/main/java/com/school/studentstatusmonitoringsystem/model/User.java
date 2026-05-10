package com.school.studentstatusmonitoringsystem.model;
import com.school.studentstatusmonitoringsystem.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, TEACHER, PARENT
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User() {
    }

    public User(Long id, String username, String password, Role role, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}