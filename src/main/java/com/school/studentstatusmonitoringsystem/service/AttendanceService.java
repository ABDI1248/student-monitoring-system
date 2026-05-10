package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.dto.AttendanceDTO;
import com.school.studentstatusmonitoringsystem.dto.AttendanceRequest;
import com.school.studentstatusmonitoringsystem.model.*;
import com.school.studentstatusmonitoringsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ParentRepository parentRepository;

    // =========================
    // 🔥 TEACHER SAVE (SECURED)
    // =========================
    public Attendance saveAttendance(AttendanceRequest request, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Teacher teacher = teacherRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 🔐 SECURITY CHECK
        if (!course.getTeacher().getId().equals(teacher.getId())) {
            throw new RuntimeException("You are not allowed to mark attendance for this course");
        }

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setStatus(request.getStatus());
        attendance.setPeriod(request.getPeriod());
        attendance.setAttendanceDate(request.getAttendanceDate());

        return attendanceRepository.save(attendance);
    }

    // =========================
    // 🔁 ENTITY → DTO
    // =========================
    private AttendanceDTO mapToDTO(Attendance a) {
        return new AttendanceDTO(
                a.getStudent().getName(),
                a.getCourse().getName(),
                a.getStatus(),
                a.getPeriod(),
                a.getAttendanceDate()
        );
    }

    // =========================
    // 📊 GET ALL
    // =========================
    public List<AttendanceDTO> getAll() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // =========================
    // 📊 BY STUDENT
    // =========================
    public List<AttendanceDTO> getByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // =========================
    // 📊 BY COURSE
    // =========================
    public List<AttendanceDTO> getByCourse(Long courseId) {
        return attendanceRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // =========================
    // 👨‍👩‍👧 PARENT VIEW (SECURE)
    // =========================
    public List<AttendanceDTO> getAttendanceForParent(String username,
                                                      LocalDate start,
                                                      LocalDate end) {

        Parent parent = parentRepository.findByUsername(username);

        if (parent == null) {
            throw new RuntimeException("Parent not found");
        }

        List<Student> students = parent.getStudents();

        List<AttendanceDTO> result = new ArrayList<>();

        for (Student student : students) {

            List<Attendance> records =
                    attendanceRepository.getStudentAttendance(
                            student.getId(), start, end
                    );

            result.addAll(
                    records.stream()
                            .map(this::mapToDTO)
                            .toList()
            );
        }

        return result;
    }

    // =========================
    // ❌ DELETE
    // =========================
    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }
}