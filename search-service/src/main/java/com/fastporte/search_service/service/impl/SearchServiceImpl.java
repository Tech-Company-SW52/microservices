package com.fastporte.search_service.service.impl;

import com.fastporte.search_service.entity.Vehicle;
import com.fastporte.search_service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    @Override
    public List<Vehicle> filterTransportByVehicleType(List<Vehicle> allVehicle, String type) {
        // Aplica el filtro para buscar servicios de transporte por nombre
        return allVehicle.stream()
                .filter(transport -> transport.getType().toLowerCase().equalsIgnoreCase(type.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByQuantity(List<Vehicle> allVehicle, Long quantity) {
        // Aplica el filtro para buscar servicios de transporte por cantidad
        return allVehicle.stream()
                .filter(transport -> transport.getQuantity() >= quantity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierRegion(List<Vehicle> allVehicle, String region) {
        // Aplica el filtro para buscar servicios de transporte por región del transportista
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getRegion().toLowerCase().equalsIgnoreCase(region.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierDescription(List<Vehicle> allVehicle, String description) {
        // Aplica el filtro para buscar servicios de transporte por descripción del transportista
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getDescription().toLowerCase().contains(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierPhone(List<Vehicle> allVehicle, String phone) {
        // Aplica el filtro para buscar servicios de transporte por teléfono del transportista
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getPhone().toLowerCase().equalsIgnoreCase(phone.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierUsername(List<Vehicle> allVehicle, String username) {
        // Aplica el filtro para buscar servicios de transporte por nombre de usuario del transportista
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getUsername().toLowerCase().equalsIgnoreCase(username.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierEmail(List<Vehicle> allVehicle, String email) {
        // Aplica el filtro para buscar servicios de transporte por correo electrónico del transportista
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getEmail().toLowerCase().equalsIgnoreCase(email.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> filterTransportByCarrierFullNameLike(List<Vehicle> allVehicle, String fullname) {
        // Aplica el filtro para buscar servicios de transporte por nombre completo del transportista
        return allVehicle.stream()
                .filter(transport -> transport.getCarrier().getFirstName().toLowerCase().contains(fullname.toLowerCase())
                        || transport.getCarrier().getLastName().toLowerCase().contains(fullname.toLowerCase()))
                .collect(Collectors.toList());
    }


}
