package com.hiring.hiringservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "El apellido no puede ser vacío")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty(message = "El username no puede ser vacío")
    @Column(name = "username", nullable = false)
    private String username;

    @NotEmpty(message = "El correo no puede ser vacío")
    @Email(message = "No es una dirección de correo bien formada")
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "La contraseña no puede ser vacía")
    @Size(min = 8, message = "El tamaño mínimo de la contraseña es 8")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthdate;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotEmpty(message = "El número de teléfono no puede ser vacío")
    @Size(min = 9, max = 9, message = "El tamaño del número de teléfono es 9")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private District district;

    @NotEmpty(message = "La región no puede ser vacía")
    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "stars")
    private int stars;
}
