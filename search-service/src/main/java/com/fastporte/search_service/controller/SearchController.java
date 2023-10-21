package com.fastporte.search_service.controller;
import com.fastporte.search_service.entity.Carrier;
import com.fastporte.search_service.entity.Vehicle;
import com.fastporte.search_service.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/search/vehicle")
public class SearchController {

    @Autowired
    SearchService searchService;
   // @Autowired
   // private RestTemplate restTemplate;


    @GetMapping("/type")
    public List<Vehicle> searchTransportByType(@RequestParam String query) {
        // Realiza una solicitud HTTP GET al endpoint de la otra API para obtener la lista de transportes
        //String otherApiUrl = "http://otra-api.com/api/transport?name=" + name;
        // List<Transport> transportList = restTemplate.getForObject(otherApiUrl, List.class);
        List<Vehicle> vehicleList = new ArrayList<>();

        // Puedes aplicar cualquier lógica adicional que necesites en la lista de transportes obtenida
        // Por ejemplo, podrías filtrar o manipular la lista según tus necesidades
        List<Vehicle> filteredVehicleList = searchService.filterTransportByVehicleType(vehicleList, query);

        return filteredVehicleList;
    }

    @GetMapping("/quantity")
    public List<Vehicle> searchTransportByQuantity(@RequestParam Long query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByQuantity(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/region")
    public List<Vehicle> searchTransportByCarrierRegion(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierRegion(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/description")
    public List<Vehicle> searchTransportByCarrierDescription(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierDescription(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/phone")
    public List<Vehicle> searchTransportByCarrierPhone(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierPhone(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/username")
    public List<Vehicle> searchTransportByCarrierUsername(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierUsername(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/email")
    public List<Vehicle> searchTransportByCarrierEmail(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierEmail(allVehicle, query);

        return filteredVehicleList;
    }

    @GetMapping("/fullname")
    public List<Vehicle> searchTransportByCarrierFullNameLike(@RequestParam String query) {
        List<Vehicle> allVehicle = new ArrayList<>();
        List<Vehicle> filteredVehicleList = searchService.filterTransportByCarrierFullNameLike(allVehicle, query);

        return filteredVehicleList;
    }

}
