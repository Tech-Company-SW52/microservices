package com.fastporte.carrierservice.entity;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El comentario no puede ser vacio")
    @Column(name="comment", nullable = false)
    private String comment;

    @NotEmpty(message = "La puntuacion no puede ser vacia")
    @Column(name = "star", nullable = false)
    private int star;

/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_client", referencedColumnName = "id")
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_carrier", referencedColumnName = "id")
    @JsonIgnore
    private Carrier carrier;
*/



}
