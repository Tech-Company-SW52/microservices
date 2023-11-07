package com.fastporte.carrierservice.repository;

import com.fastporte.carrierservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByCarrierId(Long driverId);
}