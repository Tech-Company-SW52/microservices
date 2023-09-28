package com.carrier.carrierservice.repository;


import com.carrier.carrierservice.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByCarrierId(Long carrierId);
}
