package com.client.clientservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.clientservice.entity.District;
import com.client.clientservice.service.impl.DistrictServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/district")
public class DistrictRest {
    @Autowired
    DistrictServiceImpl districtService;

    @GetMapping
    public ResponseEntity<List<District>> listAllDistricts() {
        List<District> districts = districtService.findDistrictAll();
        if (districts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(districts);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable("id") String id) {
        log.info("Fetching District with id {}", id);
        District district = districtService.findDistrictById(id);
        if (district == null) {
            log.error("District with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(district);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<District>> getDistrictByName(@PathVariable("name") String name) {
        log.info("Fetching District with name {}", name);
        List<District> districts = districtService.finDistrictByName(name);
        if (districts.isEmpty()) {
            log.error("District or Districts with name {} not found.", name);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(districts);
    }
}
