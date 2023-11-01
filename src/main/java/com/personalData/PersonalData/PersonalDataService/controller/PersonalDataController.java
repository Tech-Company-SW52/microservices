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

    // Obtener datos personales del usuario por ID y tipo de usuario
    @GetMapping("/{userType}/{id}")
    public ResponseEntity<PersonalData> getUserData(@PathVariable String userType, @PathVariable Long id) {
        return ResponseEntity.ok(personalDataService.getPersonalData(userType, id));
    }

    // Actualizar datos personales del usuario por ID y tipo de usuario
    @PutMapping("/{userType}/{id}")
    public ResponseEntity<PersonalData> updateUserData(@PathVariable String userType, @PathVariable Long id, @RequestBody PersonalData personalData) {
        return ResponseEntity.ok(personalDataService.updatePersonalData(userType, id, personalData));
    }
}
