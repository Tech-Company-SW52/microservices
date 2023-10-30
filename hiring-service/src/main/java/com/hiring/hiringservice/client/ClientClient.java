package com.hiring.hiringservice.client;

import com.hiring.hiringservice.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service")
public interface ClientClient {
    @GetMapping(value = "/client/{id}")
    ResponseEntity<Client> getClient(@PathVariable("id") long id);
}
