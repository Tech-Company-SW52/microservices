package com.fastporte.carrierservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "experiences")
@AllArgsConstructor
@NoArgsConstructor
public class Experience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private User carrier;

    @NotEmpty(message = "El trabajo no debe ser vacío")
    @Column(name = "job", nullable = false)
    private String job;

    @NotEmpty(message = "Los años no debe ser vacío")
    @Column(name = "years", nullable = false)
    private int years;
}
