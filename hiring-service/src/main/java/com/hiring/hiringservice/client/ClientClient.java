package com.hiring.hiringservice.client;

import com.hiring.hiringservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service")
public interface ClientClient {
    @GetMapping(value = "/client/{id}")
    ResponseEntity<User> getClient(@PathVariable("id") long id);
}
