package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.dto.BehaviorRequest;
import com.school.studentstatusmonitoringsystem.model.Behavior;
import com.school.studentstatusmonitoringsystem.model.BehaviorType;
import com.school.studentstatusmonitoringsystem.service.BehaviorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/behavior")
public class BehaviorController {

    @Autowired
    private BehaviorService behaviorService;

    // 👨‍🏫 TEACHER: add behavior (SECURED)
    @PostMapping
    public Behavior add(@Valid @RequestBody BehaviorRequest request,
                        Authentication authentication) {

        String username = authentication.getName();

        return behaviorService.save(request, username);
    }

    // 🔍 get all
    @GetMapping
    public List<Behavior> getAll() {
        return behaviorService.getAll();
    }

    // 🔍 by student
    @GetMapping("/student/{studentId}")
    public List<Behavior> getByStudent(@PathVariable Long studentId) {
        return behaviorService.getByStudent(studentId);
    }

    // 🔍 filter by type
    @GetMapping("/student/{studentId}/type")
    public List<Behavior> getByType(
            @PathVariable Long studentId,
            @RequestParam BehaviorType type) {

        return behaviorService.getByType(studentId, type);
    }

    // 🔍 filter by date
    @GetMapping("/date")
    public List<Behavior> getByDate(@RequestParam LocalDate date) {
        return behaviorService.getByDate(date);
    }

    // 👨‍👩‍👧 PARENT VIEW
    @GetMapping("/parent")
    public List<Behavior> getMyChildrenBehavior(Authentication authentication) {

        String username = authentication.getName();

        return behaviorService.getBehaviorForParent(username);
    }

    // ❌ delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        behaviorService.delete(id);
    }
}