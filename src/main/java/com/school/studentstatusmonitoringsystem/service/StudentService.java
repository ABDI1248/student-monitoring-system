package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.model.Student;
import com.school.studentstatusmonitoringsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // 1. Add student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // 2. Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 3. Get student by id
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // 4. Delete student
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // 5. Update student
    public Student updateStudent(Long id, Student newStudent) {
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(newStudent.getName());
            existing.setDepartment(newStudent.getDepartment());
            existing.setGradeLevel(newStudent.getGradeLevel());
            existing.setStatus(newStudent.getStatus());

            return studentRepository.save(existing);
        }

        return null;
    }
    // 6. Advanced Search + Filter students
    public List<Student> searchStudents(String status, String gradeLevel, String name) {
        return studentRepository.searchStudents(name, status, gradeLevel);
    }
}
