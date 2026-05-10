package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.dto.ParentDashboardDTO;
import com.school.studentstatusmonitoringsystem.service.ParentDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/parent-dashboard")
public class ParentDashboardController {

    @Autowired
    private ParentDashboardService parentDashboardService;

    // 🔥 SECURE DASHBOARD (NO studentId)
    @GetMapping
    public List<ParentDashboardDTO> getDashboard(Authentication authentication) {

        String username = authentication.getName();

        return parentDashboardService.getDashboard(username);
    }
}