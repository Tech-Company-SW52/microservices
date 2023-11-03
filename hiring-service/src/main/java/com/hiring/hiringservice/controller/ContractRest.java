package com.hiring.hiringservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.entity.Notification;
import com.hiring.hiringservice.entity.StatusContract;
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
import java.util.Optional;
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
    public ResponseEntity<List<Contract>> findOfferContracts(@PathVariable("id") Long id) {

        List<Contract> contracts = contractService.findByCarrierId(id);
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

    //Actualizar el status de un contrato (solo de pending a history)
    @PutMapping(value = "/{idContract}/update-status/{idContractStatus}")
    public ResponseEntity<Contract> updateContractStatusPatch(@PathVariable("idContract") Long idContract,
                                                              @PathVariable("idContractStatus") Long idContractStatus) {
        try {

            if (idContractStatus < 1 || idContractStatus > 3) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Optional<Contract> contract = contractService.getById(idContract);
            Optional<StatusContract> statusContract = statusService.getById(idContractStatus+1L);

            if (contract.isPresent() && statusContract.isPresent()) {
                Contract contractUpdate = contract.get();
                contractUpdate.setId(idContract);
                contractUpdate.setStatus(statusContract.get());
                contractService.save(contractUpdate);
                return new ResponseEntity<>(contractUpdate, HttpStatus.OK);

            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//Cambiar el status del contrato de OFFER a PENDING - aquí se añade al driver al contrato

    @PutMapping(value = "/{idContract}/change-status-offer-to-pending/carrier={idDriver}")
    public ResponseEntity<Contract> updateContractStatusOfferToPending(@PathVariable("idContract") Long idContract,
                                                                       @PathVariable("idDriver") Long idDriver) {
        try {

            Optional<Contract> contract = contractService.getById(idContract);
            Optional<StatusContract> statusContract = statusService.getById(2L);

            if (contract.isPresent() && statusContract.isPresent()) {
                Contract contractUpdate = contract.get();
                contractUpdate.setId(idContract);
                contractUpdate.setStatus(statusContract.get());
                contractUpdate.setCarrierId(idDriver);
                contractService.save(contractUpdate);
                return new ResponseEntity<>(contractUpdate, HttpStatus.OK);

            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Obetner las notificaciones no leídas de un client
    @GetMapping(value = "/unread-notifications/{user}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> findNotificationsUnreadByUser(@PathVariable("id") Long id,
                                                                        @PathVariable("user") String user) {
        try {
            List<Contract> contracts = contractService.findContractAll();
            if (user.equals("client")) {
                contracts.removeIf(contract -> !contract.getClientId().equals(id));
                contracts.removeIf(contract -> contract.getNotifications().getId().equals(1L));
            } else if (user.equals("carrier")) {
                contracts.removeIf(contract -> !contract.getCarrierId().equals(id));
                contracts.removeIf(contract -> contract.getNotifications().getId().equals(1L));
            }

            if (contracts.size() > 0)
                return new ResponseEntity<>(contracts, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Intercalar el status de una notificacion (de leida a no leida o viceversa)
    @PutMapping(value = "/{idContract}/change-notification-status")
    public ResponseEntity<Contract> updateContractNotificationPatch(@PathVariable("idContract") Long idContract) {
        try {

            Optional<Contract> contract = contractService.getById(idContract);

            if (contract.isPresent()) {

                Optional<Notification> notification;
                Contract contractUpdate = contract.get();
                contractUpdate.setId(idContract);

                if (contractUpdate.getNotifications().getId() == 2) {
                    notification = notificationService.NotificationGetById(1L);
                } else {
                    notification = notificationService.NotificationGetById(0L);
                }

                contractUpdate.setNotifications(notification.get());
                contractService.save(contractUpdate);
                return new ResponseEntity<>(contractUpdate, HttpStatus.OK);

            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
