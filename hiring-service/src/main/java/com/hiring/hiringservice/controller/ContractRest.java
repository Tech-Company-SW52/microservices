package com.hiring.hiringservice.controller;

import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.repository.ContractRepository;
import com.hiring.hiringservice.service.ContractService;
import com.hiring.hiringservice.service.impl.ContractServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/contracts")

public class ContractRest {
    @Autowired
    ContractService contractService;

    @GetMapping(value = "/contracts")
    public ResponseEntity<List<Contract>> findAllContracts() {
        try{
            List<Contract> contracts = contractService.getAll();
            if (contracts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Contract> getClient(@PathVariable("id") Long id) {
        log.info("Fetching Contract with id {}", id);
        try{
            Optional<Contract> contract = contractService.getById(id);
            if (contract == null) {
                log.error("Contract with id {} not found.", id);
                return ResponseEntity.notFound().build();
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
