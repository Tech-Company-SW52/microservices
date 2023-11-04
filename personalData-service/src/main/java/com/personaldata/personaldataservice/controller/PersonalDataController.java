package com.personaldata.personaldataservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personaldata.personaldataservice.model.Carrier;
import com.personaldata.personaldataservice.model.Client;
import com.personaldata.personaldataservice.service.IPersonalDataService;

@RestController
@RequestMapping("/personal-data")
public class PersonalDataController {
    @Autowired
    IPersonalDataService personalDataService;

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable("id") long id,
            @RequestBody Client client) {
        Client clientUpdated = personalDataService.updateClientPersonalData(id, client);
        if (clientUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientUpdated);
    }

    @PutMapping(value = "/carrier/{id}")
    public ResponseEntity<Carrier> updateCarrier(
            @PathVariable("id") long id,
            @RequestBody Carrier carrier) {
        Carrier carrierUpdated = personalDataService.updateCarrierPersonalData(id, carrier);
        if (carrierUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrierUpdated);
    }
}
