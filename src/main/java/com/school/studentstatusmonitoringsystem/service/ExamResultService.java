package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.dto.ExamResultRequest;
import com.school.studentstatusmonitoringsystem.dto.ExamSummaryDTO;
import com.school.studentstatusmonitoringsystem.model.*;
import com.school.studentstatusmonitoringsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamResultService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ExamResultRepository examResultRepository;

    // 🔥 ADD THIS
    @Autowired
    private ParentRepository parentRepository;

    // =========================
    // SAVE (SECURED LIKE ATTENDANCE)
    // =========================

    public ExamResult save(ExamResultRequest request, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Teacher teacher = teacherRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 🔥 SECURITY CHECK
        if (!course.getTeacher().getId().equals(teacher.getId())) {
            throw new RuntimeException("You are not allowed to insert results for this course");
        }

        ExamResult result = new ExamResult();
        result.setStudent(student);
        result.setCourse(course);
        result.setExamType(request.getExamType());
        result.setScore(request.getScore());
        result.setDate(request.getDate());

        return examResultRepository.save(result);
    }

    // =========================
    // BASIC
    // =========================

    public List<ExamResult> getAll() {
        return examResultRepository.findAll();
    }

    public List<ExamResult> getByStudent(Long studentId) {
        return examResultRepository.findByStudentId(studentId);
    }

    public void delete(Long id) {
        examResultRepository.deleteById(id);
    }

    public List<ExamResult> getByStudentAndCourse(Long studentId, Long courseId) {
        return examResultRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    // =========================
    // TOTAL
    // =========================

    public double calculateTotal(Long studentId, Long courseId) {

        List<ExamResult> results =
                examResultRepository.findByStudentIdAndCourseId(studentId, courseId);

        double test = 0;
        double mid = 0;
        double fin = 0;

        for (ExamResult r : results) {

            if (r.getExamType() == ExamType.TEST) {
                test = r.getScore();
            } else if (r.getExamType() == ExamType.MID) {
                mid = r.getScore();
            } else if (r.getExamType() == ExamType.FINAL) {
                fin = r.getScore();
            }
        }

        return (test * 0.2) + (mid * 0.3) + (fin * 0.5);
    }

    // =========================
    // SUMMARY PER STUDENT
    // =========================

    public List<ExamSummaryDTO> getStudentSummary(Long studentId) {

        List<ExamResult> results = examResultRepository.findByStudentId(studentId);

        Map<Long, List<ExamResult>> grouped = new HashMap<>();

        for (ExamResult r : results) {
            Long courseId = r.getCourse().getId();

            if (!grouped.containsKey(courseId)) {
                grouped.put(courseId, new ArrayList<>());
            }

            grouped.get(courseId).add(r);
        }

        List<ExamSummaryDTO> summaryList = new ArrayList<>();

        for (Long courseId : grouped.keySet()) {

            List<ExamResult> courseResults = grouped.get(courseId);

            double test = 0;
            double mid = 0;
            double fin = 0;

            String courseName = courseResults.get(0).getCourse().getName();

            for (ExamResult r : courseResults) {

                if (r.getExamType() == ExamType.TEST) {
                    test = r.getScore();
                } else if (r.getExamType() == ExamType.MID) {
                    mid = r.getScore();
                } else if (r.getExamType() == ExamType.FINAL) {
                    fin = r.getScore();
                }
            }

            double total = (test * 0.2) + (mid * 0.3) + (fin * 0.5);

            summaryList.add(
                    new ExamSummaryDTO(courseName, test, mid, fin, total)
            );
        }

        return summaryList;
    }

    // =========================
    // 🔥 PARENT VIEW (NEW)
    // =========================

    public List<ExamSummaryDTO> getSummaryForParent(String username) {

        Parent parent = parentRepository.findByUsername(username);

        if (parent == null) {
            throw new RuntimeException("Parent not found");
        }

        List<Student> students = parent.getStudents();

        List<ExamSummaryDTO> result = new ArrayList<>();

        for (Student student : students) {
            result.addAll(getStudentSummary(student.getId()));
        }

        return result;
    }
}