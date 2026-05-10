package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.model.User;
import com.school.studentstatusmonitoringsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ CREATE USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // ✅ GET ALL USERS (THIS FIXES 405)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}