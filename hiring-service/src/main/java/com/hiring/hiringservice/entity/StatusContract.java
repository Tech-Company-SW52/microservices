package com.hiring.hiringservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="status_contract")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusContract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="status", nullable = false)
    private String status;
}
