package com.personalData.PersonalData.PersonalDataService.entity;

import com.personalData.PersonalData.PersonalDataService.model.CarrierData;
import com.personalData.PersonalData.PersonalDataService.model.ClientData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "personal_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "description", nullable = false)
    private String description;
}