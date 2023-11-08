package com.client.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client.clientservice.entity.District;

public interface IDistrictRepository extends JpaRepository<District, String> {
}
