package com.fastporte.search_service.client;


import com.fastporte.search_service.entity.Vehicle;
import com.fastporte.search_service.model.Carrier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "carrier-service")
public interface CarrierClient {
    @GetMapping(value = "/carrier")
    public ResponseEntity<List<Carrier>> listAllCarriers();
    @GetMapping(value = "/carrier/vehicles")
    public ResponseEntity<List<Vehicle>> listAllVehicles();

}
