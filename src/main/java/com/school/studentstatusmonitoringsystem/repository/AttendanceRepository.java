package com.school.studentstatusmonitoringsystem.repository;

import com.school.studentstatusmonitoringsystem.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // by student
    List<Attendance> findByStudentId(Long studentId);

    // by course
    List<Attendance> findByCourseId(Long courseId);

    // by date
    List<Attendance> findByAttendanceDate(LocalDate date);

    // 🔥 parent view (student + date range)
    @Query("SELECT a FROM Attendance a WHERE " +
            "a.student.id = :studentId AND " +
            "(:start IS NULL OR a.attendanceDate >= :start) AND " +
            "(:end IS NULL OR a.attendanceDate <= :end)")
    List<Attendance> getStudentAttendance(
            @Param("studentId") Long studentId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
}