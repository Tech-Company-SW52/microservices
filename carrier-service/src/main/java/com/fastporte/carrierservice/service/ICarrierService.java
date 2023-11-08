package com.fastporte.carrierservice.service;

import com.fastporte.carrierservice.entity.User;

import java.util.List;

public interface ICarrierService {
    public User findByEmailAndPassword(String email, String password);

    public List<User> findCarrierAll();

    public User createCarrier(User carrier, String districtId);

    public User updateCarrier(User carrier, String districtId);

    public User deleteCarrier(User carrier);

    public User getCarrier(Long id);
}
