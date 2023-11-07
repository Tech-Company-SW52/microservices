package com.fastporte.carrierservice.service.impl;

import com.fastporte.carrierservice.entity.Vehicle;
import com.fastporte.carrierservice.repository.IVehicleRepository;
import com.fastporte.carrierservice.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    IVehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findByCarrierId(Long driverId) {
        return vehicleRepository.findByCarrierId(driverId);
    }

    @Override
    public List<Vehicle> findVehicleAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle vehicleDB = getVehicle(vehicle.getId());
        if (vehicleDB == null) {
            return null;
        }
        vehicleDB.setPhoto(vehicle.getPhoto());
        vehicleDB.setType(vehicle.getType());
        vehicleDB.setQuantity(vehicle.getQuantity());
        return vehicleRepository.save(vehicleDB);
    }

    @Override
    public Vehicle deleteVehicle(Vehicle vehicle) {
        Vehicle vehicleDB = getVehicle(vehicle.getId());
        if (vehicleDB == null) {
            return null;
        }
        vehicleRepository.deleteById(vehicle.getId());
        return vehicleDB;
    }

    @Override
    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }
}
