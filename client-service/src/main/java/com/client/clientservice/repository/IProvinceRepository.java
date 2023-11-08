package com.client.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client.clientservice.entity.Province;

public interface IProvinceRepository extends JpaRepository<Province, String> {
}
