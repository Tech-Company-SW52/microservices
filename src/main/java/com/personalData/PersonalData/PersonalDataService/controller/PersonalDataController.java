package com.personalData.PersonalData.PersonalDataService.controller;

import com.personalData.PersonalData.PersonalDataService.service.impl.PersonalDataServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal-data")
public class PersonalDataController {
    private final PersonalDataServiceImpl personalDataService;

    public PersonalDataController(PersonalDataServiceImpl personalDataService) {
        this.personalDataService = personalDataService;
    }

    @GetMapping("/{userType}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getUserData(@PathVariable String userType, @PathVariable Long id) {
        return ResponseEntity.ok(personalDataService.getPersonalData(userType, id));
    }

    @PutMapping("/{userType}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateUserData(@PathVariable String userType, @PathVariable Long id, @RequestBody Object personalData) {
        return ResponseEntity.ok(personalDataService.updatePersonalData(userType, id, personalData));
    }
}
