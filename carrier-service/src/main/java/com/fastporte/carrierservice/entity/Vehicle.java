package com.fastporte.carrierservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Carrier carrier;

    @Column(name = "photo", nullable = false)
    private String photo;

    @NotEmpty(message = "El tipo no debe ser vacío")
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull(message = "La cantidad no debe ser vacío")
    @Column(name = "quantity", nullable = false)
    private Long quantity;
}