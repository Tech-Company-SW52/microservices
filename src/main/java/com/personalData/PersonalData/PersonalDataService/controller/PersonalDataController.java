package com.personalData.PersonalData.PersonalDataService.controller;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.service.impl.PersonalDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personal-data")
public class PersonalDataController {

    @Autowired
    private PersonalDataServiceImpl personalDataService;

    // Obtener datos personales del usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonalData> getUserData(@PathVariable Long id) {
        return ResponseEntity.ok(personalDataService.getPersonalData(id));
    }

    // Actualizar datos personales del usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<PersonalData> updateUserData(@PathVariable Long id, @RequestBody PersonalData personalData) {
        return ResponseEntity.ok(personalDataService.updatePersonalData(id, personalData));
    }
}
