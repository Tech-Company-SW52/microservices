package com.personaldata.personaldataservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personaldata.personaldataservice.client.CarrierClient;
import com.personaldata.personaldataservice.client.ClientClient;
import com.personaldata.personaldataservice.model.Carrier;
import com.personaldata.personaldataservice.model.Client;
import com.personaldata.personaldataservice.service.IPersonalDataService;

@Service
public class PersonalDataServiceImpl implements IPersonalDataService {
    @Autowired
    ClientClient clientClient;
    @Autowired
    CarrierClient carrierClient;

    @Override
    public Client updateClientPersonalData(Long id, Client client) {
        Client clientDB = clientClient.getClient(id).getBody();
        if (clientDB == null) {
            return null;
        }
        return clientClient.updateClient(id, client).getBody();
    }

    @Override
    public Carrier updateCarrierPersonalData(Long id, Carrier carrier) {
        Carrier carrierDB = carrierClient.getCarrier(id).getBody();
        if (carrierDB == null) {
            return null;
        }
        return carrierClient.updateCarrier(id, carrier).getBody();
    }
}
