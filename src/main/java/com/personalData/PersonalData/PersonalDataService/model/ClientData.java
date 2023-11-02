package com.personalData.PersonalData.PersonalDataService.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Modelo de datos para clientes.
 */
@Data
@Builder
public class ClientData {
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
