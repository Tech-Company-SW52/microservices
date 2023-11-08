package com.hiring.hiringservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Builder
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El comentario no puede ser vacio")
    @Column(name = "comment", nullable = false)
    private String comment;

    @Min(value = 1, message = "La calificación no puede ser menor a 1")
    @Max(value = 5, message = "La calificación no puede ser mayor a 5")
    @Column(name = "stars", nullable = false)
    private int stars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Contract contract;
}
