package com.school.studentstatusmonitoringsystem.service;

import com.school.studentstatusmonitoringsystem.model.Parent;
import com.school.studentstatusmonitoringsystem.model.Student;
import com.school.studentstatusmonitoringsystem.repository.ParentRepository;
import com.school.studentstatusmonitoringsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    // 1. Save parent
    public Parent saveParent(Parent parent) {
        return parentRepository.save(parent);
    }

    // 2. Get all parents
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    // 3. Get parent by id
    public Parent getParentById(Long id) {
        return parentRepository.findById(id).orElse(null);
    }

    // 4. Delete parent
    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }

    // 🔥 Link student to parent
    public Parent assignStudent(Long parentId, Long studentId) {

        Parent parent = parentRepository.findById(parentId).orElse(null);
        Student student = studentRepository.findById(studentId).orElse(null);

        if (parent != null && student != null) {
            parent.getStudents().add(student);
            return parentRepository.save(parent);
        }

        return null;
    }
}