package com.fastporte.search_service.client;

import com.fastporte.search_service.model.User;
import com.fastporte.search_service.model.Vehicle;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "carrier-service")
public interface CarrierClient {
    @GetMapping(value = "/carrier")
    public ResponseEntity<List<User>> listAllCarriers();

    @GetMapping(value = "/carrier/vehicles")
    public ResponseEntity<List<Vehicle>> listAllVehicles();

}
