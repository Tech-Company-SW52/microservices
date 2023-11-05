package com.fastporte.carrierservice.service.impl;

import com.fastporte.carrierservice.entity.User;
import com.fastporte.carrierservice.entity.Type;
import com.fastporte.carrierservice.repository.ICarrierRepository;
import com.fastporte.carrierservice.service.ICarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarrierServiceImpl implements ICarrierService {

    @Autowired
    ICarrierRepository carrierRepository;

    @Override
    public User createCarrier(User carrier) {
        User carrierDB = carrierRepository.findByEmailAndPassword(
                carrier.getEmail(), carrier.getPassword());
        if (carrierDB != null) {
            return carrierDB;
        }
        carrier.setType(Type.CARRIER);
        carrierDB = carrierRepository.save(carrier);
        return carrierDB;
    }

    @Override
    public User updateCarrier(User carrier) {
        User carrierDB = getCarrier(carrier.getId());
        if (carrierDB == null) {
            return null;
        }
        carrierDB.setFirstName(carrier.getFirstName());
        carrierDB.setLastName(carrier.getLastName());
        carrierDB.setUsername(carrier.getUsername());
        carrierDB.setEmail(carrier.getEmail());
        carrierDB.setPassword(carrier.getPassword());
        carrierDB.setBirthdate(carrier.getBirthdate());
        carrierDB.setDescription(carrier.getDescription());
        carrierDB.setPhotoUrl(carrier.getPhotoUrl());
        carrierDB.setPhone(carrier.getPhone());
        // carrierDB.setDistrictId(carrier.getDistrictId());
        carrierDB.setStreet(carrier.getStreet());
        return carrierRepository.save(carrierDB);
    }

    @Override
    public User deleteCarrier(User carrier) {
        User carrierDB = getCarrier(carrier.getId());
        if (carrierDB == null) {
            return null;
        }
        carrierRepository.deleteById(carrier.getId());
        return carrierDB;
    }

    @Override
    public List<User> findCarrierAll() {
        List<User> carriersDB = carrierRepository.findAll();
        carriersDB.removeIf(user -> user.getType() != Type.CARRIER);
        return carriersDB;
    }

    @Override
    public User getCarrier(Long id) {
        return carrierRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return carrierRepository.findByEmailAndPassword(email, password);
    }
}
