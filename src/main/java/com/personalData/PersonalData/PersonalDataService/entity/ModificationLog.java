package com.personalData.PersonalData.PersonalDataService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entidad que registra los cambios realizados en los datos personales.
 * Cada instancia representa una modificación, capturando el tipo de
 * dato modificado, el campo, los valores anterior y nuevo, y la fecha de modificación.
 */
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
