package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long studentId;
    private String firstName;
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    public Student() {}

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId &&
                firstName.equals(student.firstName) &&
                lastName.equals(student.lastName) &&
                email.equals(student.email);
    }

}
