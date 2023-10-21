package com.fastporte.search_service.controller;
import com.fastporte.search_service.entity.Carrier;
import com.fastporte.search_service.entity.Vehicle;
import com.fastporte.search_service.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/search/vehicle")
public class SearchController {

    @Autowired
    SearchService searchService;

    //obtener data de otro microservicio
    public List<Vehicle> getVehiclesAndCarriers() {

        RestTemplate restTemplate = new RestTemplate();
        //cambiar URL
        String carriersUrl = "http://carrier-service/carriers";
        List<Carrier> carriers = restTemplate.getForObject(carriersUrl, List.class);

        //cambiar URL
        String vehiclesUrl = "http://carrier-service/carriers/vehicles";
        List<Vehicle> vehicles = restTemplate.getForObject(vehiclesUrl, List.class);

        // Agrega los carriers a los vehicles
        for (Vehicle vehicle : vehicles) {
            Carrier carrier = carriers.stream()
                    .filter(c -> c.getId().equals(vehicle.getCarrier().getId()))
                    .findFirst()
                    .orElse(null);

            vehicle.setCarrier(carrier);
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

    @GetMapping("/region")
    public List<Vehicle> searchTransportByCarrierRegion(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        allVehicle = getVehiclesAndCarriers();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierRegion(allVehicle, query);

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
