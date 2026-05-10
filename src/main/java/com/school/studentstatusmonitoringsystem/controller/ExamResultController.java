package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.dto.ExamResultRequest;
import com.school.studentstatusmonitoringsystem.dto.ExamSummaryDTO;
import com.school.studentstatusmonitoringsystem.model.ExamResult;
import com.school.studentstatusmonitoringsystem.service.ExamResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/exam-results")
public class ExamResultController {

    @Autowired
    private ExamResultService examResultService;

    // 👨‍🏫 TEACHER: add result (secured)
    @PostMapping
    public ExamResult add(@Valid @RequestBody ExamResultRequest request,
                          Authentication authentication) {

        String username = authentication.getName();

        return examResultService.save(request, username);
    }

    // 🔍 get all
    @GetMapping
    public List<ExamResult> getAll() {
        return examResultService.getAll();
    }

    // 🔍 get by student
    @GetMapping("/student/{studentId}")
    public List<ExamResult> getByStudent(@PathVariable Long studentId) {
        return examResultService.getByStudent(studentId);
    }

    // 📊 total score (per course)
    @GetMapping("/total/{studentId}/{courseId}")
    public double getTotal(@PathVariable Long studentId,
                           @PathVariable Long courseId) {
        return examResultService.calculateTotal(studentId, courseId);
    }

    // 🔥 ✅ ADD THIS (VERY IMPORTANT)
    // Student summary (used in dashboard)
    @GetMapping("/summary/{studentId}")
    public List<ExamSummaryDTO> getSummary(@PathVariable Long studentId) {
        return examResultService.getStudentSummary(studentId);
    }

    // 👨‍👩‍👧 PARENT: view children results (summary)
    @GetMapping("/parent")
    public List<ExamSummaryDTO> getMyChildrenResults(Authentication authentication) {

        String username = authentication.getName();

        return examResultService.getSummaryForParent(username);
    }

    // ❌ delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        examResultService.delete(id);
    }
}