package com.carrier.carrierservice.service;

import com.carrier.carrierservice.entity.Vehicle;

import java.util.List;

public interface IVehicleService {

    public List<Vehicle> findByCarrierId(Long driverId);
    public List<Vehicle> findVehicleAll();
    public Vehicle createVehicle(Vehicle vehicle);
    public Vehicle updateVehicle(Vehicle vehicle);
    public Vehicle deleteVehicle(Vehicle vehicle);
    public  Vehicle getVehicle(Long id);

}
