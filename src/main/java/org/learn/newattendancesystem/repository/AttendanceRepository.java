package org.learn.newattendancesystem.repository;

import org.learn.newattendancesystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByUsername(String username);

    Optional<Attendance> findByUsernameAndDate(String username, LocalDate date);

}
