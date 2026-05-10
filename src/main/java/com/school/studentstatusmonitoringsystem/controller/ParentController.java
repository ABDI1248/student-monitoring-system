package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.model.Parent;
import com.school.studentstatusmonitoringsystem.service.ParentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parents")
public class ParentController {

    @Autowired
    private ParentService parentService;

    // 1. Create parent
    @PostMapping
    public Parent createParent(@Valid @RequestBody Parent parent) {
        return parentService.saveParent(parent);
    }

    // 2. Get all parents
    @GetMapping
    public List<Parent> getAllParents() {
        return parentService.getAllParents();
    }

    // 3. Get parent by id
    @GetMapping("/{id}")
    public Parent getParentById(@PathVariable Long id) {
        return parentService.getParentById(id);
    }

    // 4. Delete parent
    @DeleteMapping("/{id}")
    public String deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
        return "Parent deleted successfully";
    }

    // 🔥 Link student to parent (parent_student table)
    @PostMapping("/{parentId}/students/{studentId}")
    public Parent assignStudentToParent(
            @PathVariable Long parentId,
            @PathVariable Long studentId) {

        return parentService.assignStudent(parentId, studentId);
    }
}