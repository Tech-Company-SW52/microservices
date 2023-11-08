package com.fastporte.carrierservice.service.impl;

import com.fastporte.carrierservice.entity.User;
import com.fastporte.carrierservice.entity.Type;
import com.fastporte.carrierservice.repository.ICarrierRepository;
import com.fastporte.carrierservice.repository.IDistrictRepository;
import com.fastporte.carrierservice.service.ICarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarrierServiceImpl implements ICarrierService {

    @Autowired
    ICarrierRepository carrierRepository;
    @Autowired
    IDistrictRepository districtRepository;

    @Override
    public User createCarrier(User carrier, String districtId) {
        User carrierDB = carrierRepository.findByEmailAndPassword(
                carrier.getEmail(), carrier.getPassword());
        if (carrierDB != null) {
            return carrierDB;
        }
        carrier.setType(Type.CARRIER);
        carrier.setDistrict(districtRepository.findById(districtId).orElse(null));
        return carrierRepository.save(carrier);
    }

    @Override
    public User updateCarrier(User carrier, String districtId) {
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
        if (districtId != carrierDB.getDistrict().getId())
            carrierDB.setDistrict(districtRepository.findById(districtId).orElse(null));
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
