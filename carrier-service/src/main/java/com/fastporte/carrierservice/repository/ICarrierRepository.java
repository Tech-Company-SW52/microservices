package com.fastporte.carrierservice.repository;

import com.fastporte.carrierservice.entity.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarrierRepository extends JpaRepository<Carrier, Long> {
    Carrier findByEmailAndPassword(String email, String password);

}