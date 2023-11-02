package com.personalData.PersonalData.PersonalDataService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// Esta clase es una entidad que representa un registro de modificación en la base de datos.
@Data
@Entity
@Table(name = "modification_log")
public class ModificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo que almacena el tipo de dato que fue modificado (CARRIER o CLIENT).
    @Enumerated(EnumType.STRING)
    private DataType dataType;

    private String modifiedField;    // Campo que indica cuál atributo fue modificado.
    private String oldValue;         // Valor anterior del atributo modificado.
    private String newValue;         // Nuevo valor del atributo modificado.

    private LocalDateTime modificationDate; // Fecha y hora en que se realizó la modificación.
}
