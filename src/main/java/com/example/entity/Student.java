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

}
