package com.fastporte.carrierservice.service;

import com.fastporte.carrierservice.entity.Carrier;

import java.util.List;

public interface ICarrierService {
    public Carrier findByEmailAndPassword(String email, String password);
    public List<Carrier> findCarrierAll();
    public Carrier createCarrier(Carrier carrier);
    public Carrier updateCarrier(Carrier carrier);
    public Carrier deleteCarrier(Carrier carrier);
    public Carrier getCarrier(Long id);

}
