package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.model.Role;
import com.school.studentstatusmonitoringsystem.model.Teacher;
import com.school.studentstatusmonitoringsystem.model.User;
import com.school.studentstatusmonitoringsystem.repository.TeacherRepository;
import com.school.studentstatusmonitoringsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ CREATE USER
    public User saveUser(User user) {

        System.out.println("Saving user... ROLE = " + user.getRole());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        System.out.println("User saved with ID: " + savedUser.getId());

        if (savedUser.getRole() == Role.TEACHER) {

            System.out.println("Creating teacher record...");

            Teacher teacher = new Teacher();
            teacher.setUserId(savedUser.getId());
            teacher.setDepartmentId(1L); // IMPORTANT: avoid null issues

            try {
                teacherRepository.save(teacher);
                System.out.println("Teacher created successfully");
            } catch (Exception e) {
                System.out.println("Teacher creation FAILED: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }
}