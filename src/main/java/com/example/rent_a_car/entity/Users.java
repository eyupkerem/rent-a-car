package com.example.rent_a_car.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Users {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @NotBlank
     @Size(min = 3, max = 20)
     @Column(name = "first_name", nullable = false, length = 20)
     private String firstName;

     @NotBlank
     @Size(min = 3, max = 20)
     @Column(name = "last_name", nullable = false, length = 20)
     private String lastName;

     @NotBlank
     @Size(max = 50)
     @Email
     @Column(name = "email", nullable = false, unique = true, length = 50)
     private String email;

     @NotBlank
     @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
     @Column(name = "phone_number", nullable = false, length = 10)
     private String phoneNumber;

     @NotBlank
     @Size(max = 120)
     @Column(name = "password", nullable = false, length = 120)
     private String password;

     @ManyToMany(fetch = FetchType.EAGER)
     @JoinTable(
             name = "user_roles" ,
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id")
     )
     @Cascade(org.hibernate.annotations.CascadeType.ALL)
     private Set<Role> roles = new HashSet<>();

}
