package com.fastporte.carrierservice.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    @Id
    private String id;

    @Column(name = "name")
    private String name;
}
