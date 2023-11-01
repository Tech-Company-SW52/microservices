package com.personalData.PersonalData.PersonalDataService.service;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import com.personalData.PersonalData.PersonalDataService.model.CarrierData;
import com.personalData.PersonalData.PersonalDataService.model.ClientData;

import java.util.List;

public interface IPersonalDataService {
    PersonalData getPersonalData(String userType, Long id);
    PersonalData updatePersonalData(String userType, Long id, PersonalData personalData);
    CarrierData syncCarrierData(Long userId, PersonalData personalData);
    ClientData syncClientData(Long userId, PersonalData personalData);
}
