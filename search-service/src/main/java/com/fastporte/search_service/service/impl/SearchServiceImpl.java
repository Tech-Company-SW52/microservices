package com.fastporte.search_service.service.impl;

import com.fastporte.search_service.model.Vehicle;
import com.fastporte.search_service.service.ISearchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements ISearchService {
    @Override
    public List<Vehicle> filterTransportByVehicleType(List<Vehicle> allVehicle, String type) {
        return allVehicle.stream()
                .filter(transport -> transport.getType().toLowerCase()
                        .equalsIgnoreCase(type.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByQuantity(List<Vehicle> allVehicle, Long quantity) {
        return allVehicle.stream()
                .filter(transport -> transport.getQuantity() >= quantity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierStreet(List<Vehicle> allVehicle, String region) {
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getStreet().toLowerCase()
                        .equalsIgnoreCase(region.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierDescription(List<Vehicle> allVehicle, String description) {
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getDescription().toLowerCase()
                        .contains(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierPhone(List<Vehicle> allVehicle, String phone) {
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getPhone().toLowerCase()
                        .equalsIgnoreCase(phone.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierUsername(List<Vehicle> allVehicle, String username) {
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getUsername().toLowerCase()
                        .equalsIgnoreCase(username.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierEmail(List<Vehicle> allVehicle, String email) {
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getEmail().toLowerCase()
                        .equalsIgnoreCase(email.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierFullNameLike(List<Vehicle> allVehicle, String fullname) {
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getFirstName().toLowerCase()
                        .contains(fullname.toLowerCase())
                        || transport.getCarrier().getLastName().toLowerCase()
                                .contains(fullname.toLowerCase()))
                .collect(Collectors.toList());
    }

}
