package org.learn.newattendancesystem.controller;

import org.learn.newattendancesystem.entity.Attendance;
import org.learn.newattendancesystem.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/attendance/")
    public String showAttendanceForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();  // Get logged-in username
        model.addAttribute("username", userName);
        model.addAttribute("today", LocalDate.now());
        return "attendanceForm";
    }

    @PostMapping("/attendance/mark")
    public String markAttendance(@RequestParam String username,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                 @RequestParam boolean present) {
        attendanceService.markAttendance(username, date, present);
        return "redirect:/attendance/view";
    }

    @GetMapping("/attendance/view")
    public String viewAttendance(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();  // Get logged-in username
        List<Attendance> attendances = attendanceService.getUserAttendance(userName);
        model.addAttribute("username",userName);
        model.addAttribute("attendances", attendances);
        return "attendanceView";
    }
}
