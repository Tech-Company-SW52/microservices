package com.personalData.PersonalData.PersonalDataService.client;

import com.personalData.PersonalData.PersonalDataService.model.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "client-service")
public interface ClientClient {
    @GetMapping(value = "/client/{id}")
    ResponseEntity<ClientData> getClient(@PathVariable("id") long id);

    @PutMapping(value = "/client/{id}")
    ResponseEntity<ClientData> updateClient(@PathVariable("id") long id, @RequestBody ClientData client);
}
