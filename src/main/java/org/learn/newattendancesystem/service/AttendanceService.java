package org.learn.newattendancesystem.service;

import org.learn.newattendancesystem.entity.Attendance;
import org.learn.newattendancesystem.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void markAttendance(String username, LocalDate date, boolean present) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("You can only mark attendance for today or future dates.");
        }

        // Check if attendance is already marked for the given date and user
        Optional<Attendance> existingAttendance = attendanceRepository.findByUsernameAndDate(username, date);
        if (existingAttendance.isPresent()) {
            throw new IllegalStateException("Attendance is already marked for this date.");
        }

        Attendance attendance = new Attendance();
        attendance.setUsername(username);
        attendance.setDate(date);
        attendance.setPresent(present);
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getUserAttendance(String username) {
        return attendanceRepository.findByUsername(username);
    }
}
