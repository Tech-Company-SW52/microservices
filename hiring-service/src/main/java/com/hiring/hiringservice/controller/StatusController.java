package com.hiring.hiringservice.controller;

import com.hiring.hiringservice.entity.StatusContract;
import com.hiring.hiringservice.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
@Api(tags="Status Contract", value="Web Service RESTful of Status Contracts")
public class StatusController {
    
}
