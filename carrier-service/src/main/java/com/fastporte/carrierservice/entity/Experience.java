package com.fastporte.carrierservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "experiences")
public class Experience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    @JsonIgnore
    private User carrier;

    @Column(name = "job", nullable = false)
    private String job;

    @Column(name = "years", nullable = false)
    private int years;
}
