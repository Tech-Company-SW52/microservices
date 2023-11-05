package com.personaldata.personaldataservice.service;

import com.personaldata.personaldataservice.model.User;

public interface IPersonalDataService {
    User updateClientPersonalData(Long id, User client);

    User updateCarrierPersonalData(Long id, User carrier);
}
