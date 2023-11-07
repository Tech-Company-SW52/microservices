package com.hiring.hiringservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.service.IContractService;
import com.hiring.hiringservice.service.INotificationService;
import com.hiring.hiringservice.service.IStatusService;
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
    @Autowired
    IStatusService statusService;

    @Autowired
    INotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Contract>> findAllContracts() {

        List<Contract> contracts = contractService.findContractAll();
        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contracts);
    }

    @GetMapping(value = "/offer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> findOfferContractsByCarrier(@PathVariable("id") Long id) {

        List<Contract> contracts = contractService.findOfferContract(id);
        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contracts);
    }

    @GetMapping(value = "/pending/{user}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> findPendingContractsByUser(@PathVariable("user") String user,
            @PathVariable("id") Long id) {

        List<Contract> contracts = contractService.findPendingContract(id, user);
        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contracts);
    }

    @GetMapping(value = "/history/{user}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> findHistoryContractsByUser(@PathVariable("user") String user,
            @PathVariable("id") Long id) {

        List<Contract> contracts = contractService.findHistoryContract(id, user);
        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contracts);
    }

    @PostMapping(value = "/add/client/{clientId}/carrier/{carrierId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PatchMapping(value = "/{idContract}/update-status/{statusContract}")
    public ResponseEntity<Contract> updateContractStatus(@PathVariable("idContract") Long idContract,
            @PathVariable("statusContract") String statusContract) {

        try {
            if (!statusContract.equals("OFFER") && !statusContract.equals("PENDING")
                    && !statusContract.equals("HISTORY")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Contract contractDB = contractService.updateStatusContract(idContract, statusContract);

            if (contractDB == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(contractDB, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/unread-notifications/{user}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> findUnreadNotificationsByUser(@PathVariable("id") Long id,
            @PathVariable("user") String user) {
        try {
            List<Contract> contracts = contractService.getUnreadNotificationsByUser(id, user);
            if (contracts.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(contracts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{idContract}/change-notification-status")
    public ResponseEntity<Contract> updateContractNotificationPatch(@PathVariable("idContract") Long idContract) {
        try {
            Contract contractDB = contractService.changeNotificationStatus(idContract);
            if (contractDB == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(contractDB, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{idContract}/change-visible")
    public ResponseEntity<Contract> updateContractVisibility(@PathVariable("idContract") Long idContract) {
        try {
            Contract contractDB = contractService.changeVisibility(idContract);
            if (contractDB == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(contractDB, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
