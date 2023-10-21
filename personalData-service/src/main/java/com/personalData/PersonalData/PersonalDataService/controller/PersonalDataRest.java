package com.personalData.PersonalData.PersonalDataService.controller;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.service.IPersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal-data")
public class PersonalDataRest {

    @Autowired
    private IPersonalDataService personalDataService;

    @GetMapping
    public ResponseEntity<List<PersonalData>> getAllData() {
        List<PersonalData> dataList = personalDataService.findPersonalDataAll();
        if (dataList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dataList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalData> getDataById(@PathVariable Long id) {
        PersonalData data = personalDataService.getPersonalData(id);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<PersonalData> createData(@RequestBody PersonalData personalData) {
        PersonalData createdData = personalDataService.createPersonalData(personalData);
        return ResponseEntity.ok(createdData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalData> updateData(@PathVariable Long id, @RequestBody PersonalData personalData) {
        personalData.setId(id);  // Asegurarse de que el ID del path coincide con el ID del objeto
        PersonalData updatedData = personalDataService.updatePersonalData(personalData);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        PersonalData dataToDelete = personalDataService.getPersonalData(id);
        if (dataToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        personalDataService.deletePersonalData(dataToDelete);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{email}/{password}")
    public ResponseEntity<PersonalData> getDataByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        PersonalData data = personalDataService.findByEmailAndPassword(email, password);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }
}
