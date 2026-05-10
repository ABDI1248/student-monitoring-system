package com.school.studentstatusmonitoringsystem.controller;

import com.school.studentstatusmonitoringsystem.model.Student;
import com.school.studentstatusmonitoringsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 1. Add student
    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // 2. Get all / Search / Filter
    @GetMapping
    public List<Student> getStudents(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String gradeLevel,
            @RequestParam(required = false) String name) {

        return studentService.searchStudents(status, gradeLevel, name);
    }


    // 3. Get student by id
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // 4. Delete student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // 5. Update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
}