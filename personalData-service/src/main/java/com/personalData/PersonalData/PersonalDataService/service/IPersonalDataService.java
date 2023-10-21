package com.personalData.PersonalData.PersonalDataService.service;

import com.personalData.PersonalData.PersonalDataService.entity.PersonalData;
import java.util.List;

public interface IPersonalDataService {

    List<PersonalData> findPersonalDataAll();

    PersonalData getPersonalData(Long id);

    PersonalData createPersonalData(PersonalData personalData);

    PersonalData updatePersonalData(PersonalData personalData);

    PersonalData deletePersonalData(PersonalData personalData);

    PersonalData findByEmailAndPassword(String email, String password);
}
