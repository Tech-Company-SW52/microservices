package com.client.clientservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.clientservice.entity.District;
import com.client.clientservice.repository.IDistrictRepository;
import com.client.clientservice.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    IDistrictRepository districtRepository;

    @Override
    public District findDistrictById(String id) {
        return districtRepository.findById(id).orElse(null);
    }

    @Override
    public List<District> findDistrictAll() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> finDistrictByName(String name) {
        return districtRepository.findByName(name);
    }

}
