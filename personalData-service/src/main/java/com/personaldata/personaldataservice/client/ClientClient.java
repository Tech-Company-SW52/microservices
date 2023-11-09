package com.personaldata.personaldataservice.client;

import com.personaldata.personaldataservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "client-service")
public interface ClientClient {
    @GetMapping(value = "/client/{id}")
    ResponseEntity<User> getClient(@PathVariable("id") long id);

    @PutMapping(value = "/client/{id}/district/{districtId}")
    ResponseEntity<User> updateClient(
            @PathVariable("id") long id,
            @PathVariable("districtId") String districtId,
            @RequestBody User client);
}
