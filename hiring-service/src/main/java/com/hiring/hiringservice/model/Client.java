package com.hiring.hiringservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String photoUrl;
    private String email;
    private String password;
    private String phone;
    private String region;
    private LocalDate birthdate;
    private String description;
}
