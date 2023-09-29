package com.personalData.PersonalData.PersonalDataService.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "modification_log")
public class ModificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DataType dataType;  // CARRIER o CLIENT

    private String modifiedField;
    private String oldValue;
    private String newValue;

    private LocalDateTime modificationDate;
}
