package com.personalData.PersonalData.PersonalDataService.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "modification_log")
public class ModificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "personal_data_id")  // Asegurándose de que exista una columna llamada 'personal_data_id'
    private Long personalDataId;  // Propiedad personalDataId

    @ManyToOne  // Añadiendo una relación ManyToOne con la entidad PersonalData
    @JoinColumn(name = "personal_data_id", insertable = false, updatable = false)  // Asociando la columna 'personal_data_id'
    private PersonalData personalData;

    @Enumerated(EnumType.STRING)
    private DataType dataType;  // CARRIER o CLIENT

    private String modifiedField;
    private String oldValue;
    private String newValue;

    private LocalDateTime modificationDate;
}
