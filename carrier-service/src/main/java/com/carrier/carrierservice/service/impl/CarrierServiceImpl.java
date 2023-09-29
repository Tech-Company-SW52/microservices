package com.carrier.carrierservice.service.impl;

import com.carrier.carrierservice.entity.Carrier;
import com.carrier.carrierservice.repository.ICarrierRepository;
import com.carrier.carrierservice.service.ICarrierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CarrierServiceImpl implements ICarrierService {

    @Autowired
    ICarrierRepository carrierRepository;

    @Override
    public Carrier createCarrier(Carrier carrier) {
        Carrier carrierDB = carrierRepository.findByEmailAndPassword(
                carrier.getEmail(),
                carrier.getPassword());

        if (carrierDB != null) {
            return carrierDB;
        }

        carrierDB = carrierRepository.save(carrier);
        return carrierDB;
    }

    @Override
    public Carrier updateCarrier(Carrier carrier) {
        Carrier carrierDB = getCarrier(carrier.getId());
        if (carrierDB == null) {
            return null;
        }
        carrierDB.setFirstName(carrier.getFirstName());
        carrierDB.setLastName(carrier.getLastName());
        carrierDB.setEmail(carrier.getEmail());
        carrierDB.setPassword(carrier.getPassword());
        carrierDB.setPhotoUrl(carrier.getPhotoUrl());
        carrierDB.setDescription(carrier.getDescription());
        carrierDB.setBirthdate(carrier.getBirthdate());
        return carrierRepository.save(carrierDB);
    }

    @Override
    public Carrier deleteCarrier(Carrier carrier) {
        Carrier carrierDB = getCarrier(carrier.getId());
        if (carrierDB == null) {
            return null;
        }
        carrierRepository.deleteById(carrier.getId());
        return carrierDB;
    }

    @Override
    public List<Carrier> findCarrierAll() {
        return carrierRepository.findAll();
    }

    @Override
    public Carrier getCarrier(Long id) {
        return carrierRepository.findById(id).orElse(null);
    }

    @Override
    public Carrier findByEmailAndPassword(String email, String password) {
        return carrierRepository.findByEmailAndPassword(email, password);
    }
}
