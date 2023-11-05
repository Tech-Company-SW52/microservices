package com.personaldata.personaldataservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.personaldata.personaldataservice.model.User;

@FeignClient(name = "carrier-service")
public interface CarrierClient {

    @GetMapping(value = "/carrier/{id}")
    ResponseEntity<User> getCarrier(@PathVariable("id") long id);

    @PutMapping(value = "/carrier/{id}")
    ResponseEntity<User> updateCarrier(@PathVariable("id") long id,
            @RequestBody User carrier);
}
