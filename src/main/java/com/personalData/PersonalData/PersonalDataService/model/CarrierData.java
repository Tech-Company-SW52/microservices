package com.personalData.PersonalData.PersonalDataService.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Modelo de datos para transportistas.
 */
@Data
@Builder
public class CarrierData {
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
