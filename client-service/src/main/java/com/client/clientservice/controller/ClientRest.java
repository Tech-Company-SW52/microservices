package com.client.clientservice.controller;

import com.client.clientservice.entity.Client;
import com.client.clientservice.service.impl.ClientServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientRest {
    @Autowired
    ClientServiceImpl clientService;

    @GetMapping
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = clientService.findClientAll();
        if (clients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        log.info("Fetching Client with id {}", id);
        Client client = clientService.getClient(id);
        if (client == null) {
            log.error("Client with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @GetMapping(value = "/searchEmailAndPassword/{email}/{password}")
    public ResponseEntity<Client> getClientByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
        log.info("Fetching Client with email {} and password {}", email, password);
        Client client = clientService.findByEmailAndPassword(email, password);
        if (client == null) {
            log.error("Client with email {} and password {} not found.", email, password);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client, BindingResult result) {
        log.info("Creating Client : {}", client);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Client clientDB = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        log.info("Updating Client with id {}", id);
        Client currentClient = clientService.getClient(id);
        if (currentClient == null) {
            log.error("Unable to update. Client with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        client.setId(id);
        currentClient = clientService.updateClient(client);
        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Client with id {}", id);
        Client client = clientService.getClient(id);
        if (client == null) {
            log.error("Unable to delete. Client with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        client = clientService.deleteClient(client);
        return ResponseEntity.ok(client);
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
