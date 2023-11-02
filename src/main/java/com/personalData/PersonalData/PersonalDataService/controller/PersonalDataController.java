package com.personalData.PersonalData.PersonalDataService.controller;

import com.personalData.PersonalData.PersonalDataService.service.impl.PersonalDataServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Este controlador gestiona las solicitudes HTTP relacionadas con los datos personales de los usuarios.
 * Utiliza PersonalDataServiceImpl para ejecutar la lógica de negocio y responder a las solicitudes de
 * obtener y actualizar datos personales.
 * Las rutas están definidas para manejar operaciones tanto para usuarios tipo "carrier" como "client".
 */
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
