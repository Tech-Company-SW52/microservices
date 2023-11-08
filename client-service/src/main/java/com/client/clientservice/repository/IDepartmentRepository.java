package com.client.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client.clientservice.entity.Department;

public interface IDepartmentRepository extends JpaRepository<Department, String> {
}
