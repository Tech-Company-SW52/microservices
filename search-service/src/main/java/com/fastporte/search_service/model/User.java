package com.fastporte.search_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private LocalDate birthdate;
    private String description;
    private String photoUrl;
    private String phone;
    private Type type;
    private District district;
    private String street;
    private int stars;
}
