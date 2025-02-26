package com.elifnursenturk.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "National ID cannot be blank")
    @Pattern(regexp = "\\d{11}", message = "National ID must be 11 digits")
    @Column(name = "tckn", unique = true, nullable = false, length = 11)
    private String tckn;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate = LocalDate.now();
    
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userid", nullable = false) 
    private User user;
}
