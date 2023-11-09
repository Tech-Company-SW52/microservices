package com.hiring.hiringservice.repository;

import com.hiring.hiringservice.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistrictRepository extends JpaRepository<District, String> {
}
