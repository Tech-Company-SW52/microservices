package com.personalData.PersonalData.PersonalDataService.service;

public interface IPersonalDataService {
    Object getPersonalData(String userType, Long id);
    Object updatePersonalData(String userType, Long id, Object personalData);
}

