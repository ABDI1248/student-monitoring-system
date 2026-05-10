package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.dto.AttendanceDTO;
import com.school.studentstatusmonitoringsystem.dto.AttendanceRequest;
import com.school.studentstatusmonitoringsystem.model.Attendance;
import com.school.studentstatusmonitoringsystem.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // 👨‍🏫 TEACHER: mark attendance
    @PostMapping
    public Attendance markAttendance(
            @Valid @RequestBody AttendanceRequest request,
            Authentication authentication) {

        String username = authentication.getName();
        return attendanceService.saveAttendance(request, username);
    }

    // 🔍 get all
    @GetMapping
    public List<AttendanceDTO> getAll() {
        return attendanceService.getAll();
    }

    // 🔍 by student
    @GetMapping("/student/{studentId}")
    public List<AttendanceDTO> getByStudent(@PathVariable Long studentId) {
        return attendanceService.getByStudent(studentId);
    }

    // 🔍 by course
    @GetMapping("/course/{courseId}")
    public List<AttendanceDTO> getByCourse(@PathVariable Long courseId) {
        return attendanceService.getByCourse(courseId);
    }

    // 👨‍👩‍👧 PARENT VIEW (SECURE)
    @GetMapping("/parent")
    public List<AttendanceDTO> getMyChildrenAttendance(
            Authentication authentication,
            @RequestParam(required = false) LocalDate start,
            @RequestParam(required = false) LocalDate end) {

        String username = authentication.getName();

        return attendanceService.getAttendanceForParent(username, start, end);
    }

    // ❌ delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        attendanceService.delete(id);
    }
}