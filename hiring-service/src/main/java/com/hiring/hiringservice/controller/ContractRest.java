package com.hiring.hiringservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.service.IContractService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/hiring/contracts")
public class ContractRest {
    @Autowired
    IContractService contractService;

    @GetMapping
    public ResponseEntity<List<Contract>> findAllContracts() {
        try {
            List<Contract> contracts = contractService.findContractAll();
            if (contracts.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/offer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> findOfferContracts(@PathVariable("id") Long id) {
        try {
            List<Contract> contracts = contractService.findOfferContract(id);
            if (contracts.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/pending/{user}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> findPendingContractsByUser(@PathVariable("user") String user,
                                                                     @PathVariable("id") Long id) {
        try {
            List<Contract> contracts = contractService.findPendingContract(id, user);
            if (contracts.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/history/{user}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> findHistoryContractsByUser(@PathVariable("user") String user,
                                                                     @PathVariable("id") Long id) {
        try {
            List<Contract> contracts = contractService.findHistoryContract(id, user);
            if (contracts.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add/{clientId}/{carrierId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contract> createContract(@PathVariable("clientId") Long clientId,
                                                   @PathVariable("carrierId") Long carrierId,
                                                   @Valid @RequestBody Contract contract,
                                                   BindingResult result) {
        log.info("Creating Contract : {}", contract);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Contract contractDB = contractService.createContract(carrierId, clientId, contract);
        return ResponseEntity.status(HttpStatus.CREATED).body(contractDB);
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
}
