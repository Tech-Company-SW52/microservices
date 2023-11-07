package com.fastporte.search_service.service;

import java.util.List;

import com.fastporte.search_service.model.Vehicle;

public interface ISearchService {
    public List<Vehicle> filterTransportByVehicleType(List<Vehicle> allVehicle, String type);

    public List<Vehicle> filterTransportByQuantity(List<Vehicle> allVehicle, Long quantity);

    public List<Vehicle> filterTransportByCarrierStreet(List<Vehicle> allVehicle, String region);

    public List<Vehicle> filterTransportByCarrierDescription(List<Vehicle> allVehicle, String description);

    public List<Vehicle> filterTransportByCarrierPhone(List<Vehicle> allVehicle, String phone);

    public List<Vehicle> filterTransportByCarrierUsername(List<Vehicle> allVehicle, String username);

    public List<Vehicle> filterTransportByCarrierEmail(List<Vehicle> allVehicle, String email);

    public List<Vehicle> filterTransportByCarrierFullNameLike(List<Vehicle> allVehicle, String fullname);
}
