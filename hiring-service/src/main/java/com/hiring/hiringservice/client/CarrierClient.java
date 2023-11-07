package com.hiring.hiringservice.client;

import com.hiring.hiringservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrier-service")
public interface CarrierClient {
    @GetMapping(value = "/carrier/{id}")
    ResponseEntity<User> getCarrier(@PathVariable("id") long id);
}
