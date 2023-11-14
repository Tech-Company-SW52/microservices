package com.personaldata.personaldataservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personaldata.personaldataservice.client.CarrierClient;
import com.personaldata.personaldataservice.client.ClientClient;
import com.personaldata.personaldataservice.model.User;
import com.personaldata.personaldataservice.service.IPersonalDataService;

@Service
public class PersonalDataServiceImpl implements IPersonalDataService {
    @Autowired
    ClientClient clientClient;
    @Autowired
    CarrierClient carrierClient;

    @Override
    public User updateClientPersonalData(Long id, String districtId, User client) {
        User clientDB = clientClient.getClient(id).getBody();
        if (clientDB == null) {
            return null;
        }
        return clientClient.updateClient(id, districtId, client).getBody();
    }

    @Override
    public User updateCarrierPersonalData(Long id, String districtId, User carrier) {
        User carrierDB = carrierClient.getCarrier(id).getBody();
        if (carrierDB == null) {
            return null;
        }
        return carrierClient.updateCarrier(id, districtId, carrier).getBody();
    }
}
