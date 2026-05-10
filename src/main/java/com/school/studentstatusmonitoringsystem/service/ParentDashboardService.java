package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.dto.*;
import com.school.studentstatusmonitoringsystem.model.*;
import com.school.studentstatusmonitoringsystem.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParentDashboardService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private ExamResultService examResultService;

    @Autowired
    private BehaviorService behaviorService;

    // =========================
    // 🔥 SECURE DASHBOARD (BY USER)
    // =========================
    public List<ParentDashboardDTO> getDashboard(String username) {

        Parent parent = parentRepository.findByUsername(username);

        if (parent == null) {
            throw new RuntimeException("Parent not found");
        }

        List<ParentDashboardDTO> dashboards = new ArrayList<>();

        for (Student student : parent.getStudents()) {

            ParentDashboardDTO dto = new ParentDashboardDTO();

            // =========================
            // 1. STUDENT NAME
            // =========================
            dto.setStudentName(student.getName());

            // =========================
            // 2. ATTENDANCE (FIXED SAFE VERSION)
            // =========================
            List<AttendanceDTO> attendanceList =
                    attendanceService.getByStudent(student.getId());

            dto.setAttendance(attendanceList);

            // =========================
            // 3. EXAM RESULTS (SAFE)
            // =========================
            List<ExamSummaryDTO> summaries =
                    examResultService.getStudentSummary(student.getId());

            dto.setExamResults(summaries);

            // =========================
            // 4. BEHAVIOR
            // =========================
            dto.setBehavior(behaviorService.getByStudent(student.getId()));

            dashboards.add(dto);
        }

        return dashboards;
    }
}