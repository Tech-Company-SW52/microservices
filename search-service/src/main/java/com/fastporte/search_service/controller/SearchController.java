package com.fastporte.search_service.controller;

import com.fastporte.search_service.client.CarrierClient;

import com.fastporte.search_service.model.User;
import com.fastporte.search_service.model.Vehicle;
import com.fastporte.search_service.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search/vehicle")
public class SearchController {

    @Autowired
    ISearchService searchService;

    @Autowired
    CarrierClient carrierClient;

    public List<Vehicle> getVehiclesAndCarriers() {

        ResponseEntity<List<User>> responseCarriers = carrierClient.listAllCarriers();
        List<User> carriers = responseCarriers.getBody();

        ResponseEntity<List<Vehicle>> responseVehicles = carrierClient.listAllVehicles();
        List<Vehicle> vehicles = responseVehicles.getBody();

        if (vehicles != null) {
            for (Vehicle vehicle : vehicles) {
                User carrier = carriers.stream()
                        .filter(c -> c.getId().equals(vehicle.getCarrier().getId()))
                        .findFirst()
                        .orElse(null);

                vehicle.setCarrier(carrier);
            }
        } else {
            vehicles = new ArrayList<>();
        }

        return vehicles;
    }

    @GetMapping("/type")
    public List<Vehicle> searchTransportByType(@RequestParam String query) {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByVehicleType(vehicleList, query);

        return filteredVehicleList;
    }

    @GetMapping("/quantity")
    public List<Vehicle> searchTransportByQuantity(@RequestParam Long query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        allVehicle = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByQuantity(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/street")
    public List<Vehicle> searchTransportByCarrierRegion(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        allVehicle = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierStreet(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/description")
    public List<Vehicle> searchTransportByCarrierDescription(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        allVehicle = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierDescription(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/phone")
    public List<Vehicle> searchTransportByCarrierPhone(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        allVehicle = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierPhone(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/username")
    public List<Vehicle> searchTransportByCarrierUsername(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        allVehicle = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierUsername(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/email")
    public List<Vehicle> searchTransportByCarrierEmail(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        allVehicle = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierEmail(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/fullname")
    public List<Vehicle> searchTransportByCarrierFullNameLike(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        allVehicle = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierFullNameLike(allVehicle, query);

        return filteredVehicleList;
    }

}
