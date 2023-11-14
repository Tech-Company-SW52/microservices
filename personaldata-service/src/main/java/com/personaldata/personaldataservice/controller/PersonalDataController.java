package com.personaldata.personaldataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personaldata.personaldataservice.model.User;
import com.personaldata.personaldataservice.service.IPersonalDataService;

@RestController
@RequestMapping("/personal-data")
public class PersonalDataController {
    @Autowired
    IPersonalDataService personalDataService;

    @PutMapping(value = "/client/{id}/district/{districtId}")
    public ResponseEntity<User> updateClient(
            @PathVariable("id") long id,
            @PathVariable("districtId") String districtId,
            @RequestBody User client) {

        User clientUpdated = personalDataService.updateClientPersonalData(id, districtId, client);
        if (clientUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientUpdated);
    }

    @PutMapping(value = "/carrier/{id}/district/{districtId}")
    public ResponseEntity<User> updateCarrier(
            @PathVariable("id") long id,
            @PathVariable("districtId") String districtId,
            @RequestBody User carrier) {

        User carrierUpdated = personalDataService.updateCarrierPersonalData(id, districtId, carrier);
        if (carrierUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrierUpdated);
    }
}
