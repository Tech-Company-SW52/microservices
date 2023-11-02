package com.fastporte.search_service.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Data
@Builder
@Getter
@Setter

public class Carrier {

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
