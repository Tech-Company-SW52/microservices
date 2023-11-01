package com.personalData.PersonalData.PersonalDataService.client;

import com.personalData.PersonalData.PersonalDataService.model.CarrierData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "carrier-service")
public interface CarrierClient {

    @GetMapping(value = "/carrier/{id}")
    ResponseEntity<CarrierData> getCarrierData(@PathVariable("id") long id);

    @PutMapping(value = "/carrier/{id}")
    ResponseEntity<CarrierData> updateCarrier(@PathVariable("id") long id, @RequestBody CarrierData carrier);
}
