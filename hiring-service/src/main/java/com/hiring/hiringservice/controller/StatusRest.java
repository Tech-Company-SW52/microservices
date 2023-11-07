package com.hiring.hiringservice.controller;

import com.hiring.hiringservice.entity.Contract;
import com.hiring.hiringservice.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hiring/contract/status")
public class StatusRest {

    @Autowired
    IStatusService statusService;

    @PostMapping(value = "/{status}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contract> findOfferContracts(@PathVariable("status") String status,
            @PathVariable("id") Long id) {
        Contract contract = statusService.updateStatusMode(id, status.toLowerCase());
        return ResponseEntity.ok(contract);
    }
}
