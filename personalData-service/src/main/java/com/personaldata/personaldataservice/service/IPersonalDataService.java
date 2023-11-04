package com.personaldata.personaldataservice.service;

import com.personaldata.personaldataservice.model.Carrier;
import com.personaldata.personaldataservice.model.Client;

public interface IPersonalDataService {
    Client updateClientPersonalData(Long id, Client client);

    Carrier updateCarrierPersonalData(Long id, Carrier carrier);
}
