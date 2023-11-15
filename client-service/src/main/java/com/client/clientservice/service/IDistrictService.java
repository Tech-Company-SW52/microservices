package com.client.clientservice.service;

import java.util.List;

import com.client.clientservice.entity.District;

public interface IDistrictService {
    public District findDistrictById(String id);

    public List<District> findDistrictAll();

    public District finDistrictByName(String name);
}
