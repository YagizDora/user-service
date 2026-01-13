package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String surname;
    
    @Column(unique = true)
    private String email;
    
    private String phoneNumber;
    private Integer age;
    
    // 11-digit Gov ID
    @Column(length = 11)
    private String govId;
}