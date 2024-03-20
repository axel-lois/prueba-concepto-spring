package com.example.service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(long id) {
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get();
        }
        return null;
    }

    public void saveOrUpdateStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

}
