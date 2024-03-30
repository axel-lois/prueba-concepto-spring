package com.example.controller;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @GetMapping(path = "/{id}")
    public Student getStudentById(@PathVariable("id") Long id) {
        return this.studentService.getStudentById(id);
    }

    @PostMapping
    public void saveUpdate(@RequestBody Student student) {
        this.studentService.saveOrUpdateStudent(student);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        this.studentService.deleteStudentById(id);
    }

}
