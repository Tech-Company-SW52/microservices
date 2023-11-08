package com.fastporte.carrierservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastporte.carrierservice.entity.District;

public interface IDistrictRepository extends JpaRepository<District, String> {
}
