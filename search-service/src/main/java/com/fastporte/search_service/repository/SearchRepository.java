package com.fastporte.search_service.repository;

import com.fastporte.search_service.entity.Vehicle;

import java.util.List;

public interface SearchRepository {

    public List<Vehicle> filerTransportByName(List<Vehicle> allVehicle, String name);

}
