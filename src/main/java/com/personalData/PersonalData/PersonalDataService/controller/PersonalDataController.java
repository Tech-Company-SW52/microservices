package com.personalData.PersonalData.PersonalDataService.controller;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.service.impl.PersonalDataServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personal-data")
public class PersonalDataController {
    // Inyectar dependencia de PersonalDataServiceImpl reemplazando la anotación @Autowired
    private final PersonalDataServiceImpl personalDataService;

    public PersonalDataController(PersonalDataServiceImpl personalDataService) {
        this.personalDataService = personalDataService;
    }

    // Obtener datos personales del usuario por ID y tipo de usuario
    @GetMapping("/{userType}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonalData> getUserData(@PathVariable String userType, @PathVariable Long id) {
        return ResponseEntity.ok(personalDataService.getPersonalData(userType, id));
    }

    // Actualizar datos personales del usuario por ID y tipo de usuario
    @PutMapping("/{userType}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonalData> updateUserData(@PathVariable String userType, @PathVariable Long id, @RequestBody PersonalData personalData) {
        return ResponseEntity.ok(personalDataService.updatePersonalData(userType, id, personalData));
    }
}
