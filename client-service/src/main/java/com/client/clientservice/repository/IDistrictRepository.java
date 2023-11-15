package com.client.clientservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client.clientservice.entity.District;

public interface IDistrictRepository extends JpaRepository<District, String> {
    public List<District> findByName(String name);
}
