// package com.fastporte.carrierservice.entity;

// import lombok.Data;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.NotEmpty;
// import java.io.Serializable;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @Data
// @Entity
// @Table(name = "comments")
// public class Comment implements Serializable {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotEmpty(message = "El comentario no puede ser vacio")
//     @Column(name = "comment", nullable = false)
//     private String comment;

//     @NotEmpty(message = "La puntuacion no puede ser vacia")
//     @Column(name = "stars", nullable = false)
//     private int stars;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id_client", referencedColumnName = "id")
//     @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//     private User client;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id_carrier", referencedColumnName = "id")
//     @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//     private User carrier;

// }
