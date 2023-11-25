package com.example.StudentManagment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)
    @NotEmpty
    private String photoUrl;

    @Column(nullable = false)
    @Pattern(regexp = "\\d{10}", message = "Phone number should be in the format XXXXXXXXXX")
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank
    private String email;
}
