package com.carrier.carrierservice.controller;

import com.carrier.carrierservice.entity.Carrier;
import com.carrier.carrierservice.entity.Experience;
import com.carrier.carrierservice.entity.Vehicle;
import com.carrier.carrierservice.service.impl.CarrierServiceImpl;
import com.carrier.carrierservice.service.impl.ExperienceServiceImpl;
import com.carrier.carrierservice.service.impl.VehicleServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/carriers")
public class CarrierRest {
    @Autowired
    CarrierServiceImpl carrierService;

    @Autowired
    VehicleServiceImpl vehicleService;

    @Autowired
    ExperienceServiceImpl experienceService;

    @GetMapping
    public ResponseEntity<List<Carrier>> listAllCarriers() {
        List<Carrier> carriers = carrierService.findCarrierAll();
        if (carriers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carriers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carrier> getCarrier(@PathVariable("id") Long id) {
        log.info("Fetching Carrier with id {}", id);
        Carrier carrier = carrierService.getCarrier(id);
        if (carrier == null) {
            log.error("Carrier with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrier);
    }

    @GetMapping(value = "/searchEmailAndPassword/{email}/{password}")
    public ResponseEntity<Carrier> getCarrierByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
        log.info("Fetching Carrier with email {} and password {}", email, password);
        Carrier carrier = carrierService.findByEmailAndPassword(email, password);
        if (carrier == null) {
            log.error("Carrier with email {} and password {} not found.", email, password);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrier);
    }

    @PostMapping
    public ResponseEntity<Carrier> createCarrier(@Valid @RequestBody Carrier carrier, BindingResult result){
        log.info("Creating Carrier : {}", carrier);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Carrier carrierDB = carrierService.createCarrier(carrier);
        return ResponseEntity.ok(carrierDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Carrier> updateCarrier(@PathVariable("id") long id, @RequestBody Carrier carrier) {
        log.info("Updating Carrier with id {}", id);
        Carrier currentCarrier = carrierService.getCarrier(id);
        if (currentCarrier == null) {
            log.error("Unable to update. Carrier with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        carrier.setId(id);
        currentCarrier = carrierService.updateCarrier(carrier);
        return ResponseEntity.ok(currentCarrier);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Carrier> deleteCarrier(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Carrier with id {}", id);
        Carrier carrier = carrierService.getCarrier(id);
        if (carrier == null) {
            log.error("Unable to delete. Carrier with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        carrier = carrierService.deleteCarrier(carrier);
        return ResponseEntity.ok(carrier);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @GetMapping(value = "/{id}/vehicles")
    public ResponseEntity<List<Vehicle>> listAllVehiclesByDriverId(@PathVariable("id") Long id) {
        List<Vehicle> vehicles = vehicleService.findByCarrierId(id);
        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(value = "/{id}/vehicles/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleByDriverId(@PathVariable("id") Long id, @PathVariable("vehicleId") Long vehicleId) {
        log.info("Fetching Vehicle with id {}", vehicleId);
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        if (vehicle == null) {
            log.error("Vehicle with id {} not found.", vehicleId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping(value = "/{id}/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@PathVariable("id") Long id, @Valid @RequestBody Vehicle vehicle, BindingResult result){
        log.info("Creating Vehicle : {}", vehicle);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Carrier carrier = carrierService.getCarrier(id);
        if (carrier == null) {
            log.error("Unable to create. Carrier with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        vehicle.setCarrier(carrier);
        Vehicle vehicleDB = vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok(vehicleDB);
    }

    @PutMapping(value = "/{id}/vehicles/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") Long id, @PathVariable("vehicleId") Long vehicleId, @RequestBody Vehicle vehicle) {
        log.info("Updating Vehicle with id {}", vehicleId);
        Vehicle currentVehicle = vehicleService.getVehicle(vehicleId);
        if (currentVehicle == null) {
            log.error("Unable to update. Vehicle with id {} not found.", vehicleId);
            return ResponseEntity.notFound().build();
        }
        Carrier carrier = carrierService.getCarrier(id);
        if (carrier == null) {
            log.error("Unable to update. Carrier with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        vehicle.setCarrier(carrier);
        vehicle.setId(vehicleId);
        currentVehicle = vehicleService.updateVehicle(vehicle);
        return ResponseEntity.ok(currentVehicle);
    }

    @DeleteMapping(value = "/{id}/vehicles/{vehicleId}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") Long id, @PathVariable("vehicleId") Long vehicleId) {
        log.info("Fetching & Deleting Vehicle with id {}", vehicleId);
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        if (vehicle == null) {
            log.error("Unable to delete. Vehicle with id {} not found.", vehicleId);
            return ResponseEntity.notFound().build();
        }
        vehicle = vehicleService.deleteVehicle(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<List<Vehicle>> listAllVehicles() {
        List<Vehicle> vehicles = vehicleService.findVehicleAll();
        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(value = "/{id}/experiences")
    public ResponseEntity<List<Experience>> listAllExperiencesByCarrierId(@PathVariable("id") Long id) {
        List<Experience> experiences = experienceService.findByCarrierId(id);
        if (experiences.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(experiences);
    }

    @GetMapping(value = "/{id}/experiences/{experienceId}")
    public ResponseEntity<Experience> getExperienceByCarrierId(@PathVariable("id") Long id, @PathVariable("experienceId") Long experienceId) {
        log.info("Fetching Experience with id {}", experienceId);
        Experience experience = experienceService.getExperience(experienceId);
        if (experience == null) {
            log.error("Experience with id {} not found.", experienceId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(experience);
    }

    @PostMapping(value = "/{id}/experiences")
    public ResponseEntity<Experience> createExperience(@PathVariable("id") Long id, @Valid @RequestBody Experience experience, BindingResult result){
        log.info("Creating Experience : {}", experience);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Carrier carrier = carrierService.getCarrier(id);
        if (carrier == null) {
            log.error("Unable to create. Carrier with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        experience.setCarrier(carrier);
        Experience experienceDB = experienceService.createExperience(experience);
        return ResponseEntity.ok(experienceDB);
    }

    @PutMapping(value = "/{id}/experiences/{experienceId}")
    public ResponseEntity<Experience> updateExperience(@PathVariable("id") Long id, @PathVariable("experienceId") Long experienceId, @RequestBody Experience experience) {
        log.info("Updating Experience with id {}", experienceId);
        Experience currentExperience = experienceService.getExperience(experienceId);
        if (currentExperience == null) {
            log.error("Unable to update. Experience with id {} not found.", experienceId);
            return ResponseEntity.notFound().build();
        }
        Carrier carrier = carrierService.getCarrier(id);
        if (carrier == null) {
            log.error("Unable to update. Carrier with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        experience.setCarrier(carrier);
        experience.setId(experienceId);
        currentExperience = experienceService.updateExperience(experience);
        return ResponseEntity.ok(currentExperience);
    }

    @DeleteMapping(value = "/{id}/experiences/{experienceId}")
    public ResponseEntity<Experience> deleteExperience(@PathVariable("id") Long id, @PathVariable("experienceId") Long experienceId) {
        log.info("Fetching & Deleting Experience with id {}", experienceId);
        Experience experience = experienceService.getExperience(experienceId);
        if (experience == null) {
            log.error("Unable to delete. Experience with id {} not found.", experienceId);
            return ResponseEntity.notFound().build();
        }
        experience = experienceService.deleteExperience(experience);
        return ResponseEntity.ok(experience);
    }

    @GetMapping(value = "/experiences")
    public ResponseEntity<List<Experience>> listAllExperiences() {
        List<Experience> experiences = experienceService.findExperienceAll();
        if (experiences.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(experiences);
    }

}